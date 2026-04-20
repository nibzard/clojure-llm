(defn tri [n]
  (loop [i 0
         res []]
    (if (> i n)
      res
      (let [val (cond
                  (zero? i) 1
                  (= i 1) 3
                  (even? i) (inc (quot i 2))
                  :else (+ (res (- i 2)) i 2))]
        (recur (inc i) (conj res val))))))