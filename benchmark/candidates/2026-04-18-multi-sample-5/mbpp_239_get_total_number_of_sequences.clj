(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (cond
    (or (nil? m) (nil? n) (not (pos-int? m)) (not (pos-int? n))) 0
    (= n 1) m
    :else
    (letfn [(count-seq [max-val len]
              (cond
                (= len 1) max-val
                (<= max-val 0) 0
                :else
                (reduce + (map #(count-seq (quot max-val 2) (dec len))
                               (range 1 (inc max-val))))))]
      (let [memoized-count
            (memoize
             (fn count-seq [max-val len]
               (cond
                 (= len 1) max-val
                 (<= max-val 0) 0
                 :else
                 (+ (count-seq (dec max-val) len)
                    (count-seq (quot max-val 2) (dec len))))))]
        (memoized-count m n)))))