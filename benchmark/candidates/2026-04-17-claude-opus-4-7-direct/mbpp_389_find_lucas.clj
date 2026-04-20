(defn find_lucas
  "Write a function to find the n'th lucas number."
  [n]
  (cond
    (nil? n) nil
    (not (integer? n)) (throw (IllegalArgumentException. "n must be an integer"))
    (neg? n) (throw (IllegalArgumentException. "n must be non-negative"))
    (zero? n) 2
    (= n 1) 1
    :else (loop [i 2
                 l-prev2 2  ;; L(0)
                 l-prev1 1] ;; L(1)
            (if (= i n)
              (+ l-prev2 l-prev1)
              (recur (inc i) l-prev1 (+ l-prev2 l-prev1))))))