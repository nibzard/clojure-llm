(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-seqs [max-val len]
            (cond
              (or (nil? max-val) (nil? len) (<= max-val 0) (<= len 0)) 0
              (= len 1) max-val
              :else
              (reduce
               +
               (map #(count-seqs (quot max-val 2) (dec len))
                    (range 1 (inc max-val))))))
          (dp-count [m n]
            (let [memo (atom {})]
              (letfn [(f [max-val len]
                        (if-let [v (get @memo [max-val len])]
                          v
                          (let [result
                                (cond
                                  (or (<= max-val 0) (<= len 0)) 0
                                  (= len 1) max-val
                                  :else
                                  (reduce
                                   +
                                   (for [last-val (range 1 (inc max-val))]
                                     (f (quot last-val 2) (dec len)))))]
                            (swap! memo assoc [max-val len] result)
                            result)))]
                (f m n)))]
    (if (or (nil? m) (nil? n) (<= m 0) (<= n 0))
      0
      (let [f (memoize
               (fn f [max-val len]
                 (cond
                   (or (<= max-val 0) (<= len 0)) 0
                   (= len 1) max-val
                   :else
                   (+ (f (dec max-val) len)
                      (f (quot max-val 2) (dec len))))))]
        (f m n)))))