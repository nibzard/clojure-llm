(defn fizz_buzz
  "Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13."
  [n]
  (->> (range n)
       (filter (fn [x] (or (zero? (mod x 11)) (zero? (mod x 13)))))
       (map str)
       (apply str)
       (filter #(= \7 %))
       count))