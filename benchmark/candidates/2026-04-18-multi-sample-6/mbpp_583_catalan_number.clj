(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (cond
            (nil? num) 0
            (number? num) (long num)
            :else 0)]
    (if (neg? n)
      0
      (let [binom (fn [n k]
                    (let [k (min k (- n k))]
                      (loop [i 1N
                             acc 1N]
                        (if (> i k)
                          acc
                          (recur (inc i) (/ (* acc (- n (dec i))) i))))))]
        (/ (binom (* 2 n) n) (inc n))))))