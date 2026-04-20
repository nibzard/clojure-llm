(defn get_total_number_of_sequences
  "	Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-seq [max-val len]
            (cond
              (or (nil? max-val) (nil? len) (<= max-val 0) (<= len 0)) 0
              (= len 1) max-val
              :else
              (reduce + (map #(count-seq (quot max-val 2) (dec len))
                             (range 1 (inc max-val))))))
          (count-dp [max-val len]
            (let [memo (atom {})]
              (letfn [(f [i j]
                        (if-let [v (get @memo [i j])]
                          v
                          (let [res (cond
                                      (<= i 0) 0
                                      (<= j 0) 0
                                      (= j 1) i
                                      :else (+ (f (dec i) j)
                                               (f (quot i 2) (dec j))))]
                            (swap! memo assoc [i j] res)
                            res)))]
                (f max-val len))))]
    (count-dp m n)))