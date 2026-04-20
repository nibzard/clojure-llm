(defn is_multiply_prime [a]
  (if (<= a 1)
    false
    (let [factors-count (loop [n a
                               d 2
                               acc 0]
                          (cond
                            (= n 1) acc
                            (> acc 3) acc
                            (zero? (mod n d)) (recur (quot n d) d (inc acc))
                            :else (recur n (inc d) acc)))]
      (= 3 factors-count))))