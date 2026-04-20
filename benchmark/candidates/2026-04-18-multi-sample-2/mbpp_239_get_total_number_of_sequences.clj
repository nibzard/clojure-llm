(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-ending-at [x len memo]
            (if (= len 1)
              [1 memo]
              (if-let [cached (get-in memo [x len])]
                [cached memo]
                (let [[total memo']
                      (loop [k 1
                             acc 0
                             memo memo]
                        (if (> (* 2 k) x)
                          [acc memo]
                          (let [[cnt memo'] (count-ending-at k (dec len) memo)]
                            (recur (inc k) (+ acc cnt) memo'))))]
                  [total (assoc-in memo' [x len] total)]))))]
    (if (or (nil? m) (nil? n) (<= m 0) (<= n 0))
      0
      (first
       (loop [x 1
              acc 0
              memo {}]
         (if (> x m)
           [acc memo]
           (let [[cnt memo'] (count-ending-at x n memo)]
             (recur (inc x) (+ acc cnt) memo'))))))))