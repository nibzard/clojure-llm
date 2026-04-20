(defn fizz_buzz
  "Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.
  >>> (fizz_buzz 50)
  0
  >>> (fizz_buzz 78)
  2
  >>> (fizz_buzz 79)
  3"
  [n]
  (let [count-7s (fn [x]
                   (->> (str x)
                        (filter #(= % \7))
                        count))]
    (->> (range n)
         (filter #(or (zero? (mod % 11))
                      (zero? (mod % 13))))
         (map count-7s)
         (reduce + 0))))