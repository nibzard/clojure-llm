(defn newman_prime [n]
  (if (<= n 0)
    1
    (loop [i 1
           u-prev 1
           u-curr 7]
      (if (= i n)
        u-curr
        (recur (inc i) u-curr (- (* 6 u-curr) u-prev))))))