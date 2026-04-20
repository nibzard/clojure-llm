(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-seq [max-val len]
            (cond
              (or (nil? max-val) (nil? len) (<= max-val 0) (<= len 0)) 0
              (= len 1) max-val
              :else
              (reduce + (map #(count-seq (quot max-val 2) (dec len))
                             (range 1 (inc max-val))))))]
    (if (or (nil? m) (nil? n) (<= m 0) (<= n 0))
      0
      (let [memoized-count
            (memoize
             (fn count-seq [max-val len]
               (cond
                 (<= max-val 0) 0
                 (= len 1) max-val
                 :else
                 (reduce
                  +
                  (map #(memoized-count (quot % 2) (dec len))
                       (range 1 (inc max-val)))))))]
        (memoized-count m n))))