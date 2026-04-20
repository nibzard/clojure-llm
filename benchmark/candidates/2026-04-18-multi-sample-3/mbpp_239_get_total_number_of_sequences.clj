(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (cond
    (or (nil? m) (nil? n) (not (pos? m)) (not (pos? n))) 0
    (= n 1) m
    :else
    (letfn [(count-seqs [max-val len]
              (cond
                (= len 1) max-val
                (<= max-val 0) 0
                :else
                (reduce + (map #(count-seqs (quot max-val 2) (dec len))
                               (range 1 (inc max-val))))))]
      (let [memoized-count
            (memoize
             (fn count-seqs [max-val len]
               (cond
                 (= len 1) max-val
                 (<= max-val 0) 0
                 :else
                 (reduce + (map (fn [last-val]
                                  (memoized-count (quot (dec last-val) 2) (dec len)))
                                (range 1 (inc max-val)))))))]
        (memoized-count m n))))