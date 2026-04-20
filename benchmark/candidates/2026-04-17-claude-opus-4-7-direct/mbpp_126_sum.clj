(defn sum
  "Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (let [gcd (fn [x y]
              (cond
                (zero? y) x
                :else (recur y (mod x y))))
        d (gcd a b)
        divisors (filter #(zero? (mod d %)) (range 1 (inc d)))]
    (reduce + 0 divisors)))