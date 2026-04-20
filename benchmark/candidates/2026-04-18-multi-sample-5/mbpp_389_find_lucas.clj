(defn find_lucas
  "	Write a function to find the n'th lucas number."
  [n]
  (when (and (integer? n) (not (neg? n)))
    (cond
      (= n 0) 2
      (= n 1) 1
      :else (loop [i 2
                   a 2
                   b 1]
              (if (> i n)
                b
                (recur (inc i) b (+ a b)))))))