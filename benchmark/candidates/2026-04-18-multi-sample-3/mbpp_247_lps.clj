(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [idxs (range n)]
        (loop [dp (vec (repeat n (vec (repeat n 0))))
               len 1]
          (if (> len n)
            (get-in dp [0 (dec n)])
            (let [dp' (if (= len 1)
                        (reduce (fn [m i] (assoc-in m [i i] 1)) dp idxs)
                        (reduce
                         (fn [m i]
                           (let [j (+ i len -1)]
                             (assoc-in
                              m
                              [i j]
                              (if (= (nth s i) (nth s j))
                                (if (= len 2)
                                  2
                                  (+ 2 (get-in m [(inc i) (dec j)] 0)))
                                (max (get-in m [(inc i) j] 0)
                                     (get-in m [i (dec j)] 0))))))
                         dp
                         (range 0 (inc (- n len)))))]
              (recur dp' (inc len))))))))