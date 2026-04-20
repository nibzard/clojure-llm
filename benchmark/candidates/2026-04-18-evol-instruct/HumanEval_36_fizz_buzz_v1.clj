(defn fizz_buzz
  "Return the sum of all digits 7 appearing in the decimal representations of integers less than n
   that are divisible by 11 or 13.

   Count repeated 7s in the same number separately.

   Examples:
   >>> (fizz_buzz 50)
   0
   >>> (fizz_buzz 78)
   2
   >>> (fizz_buzz 79)
   3

   Handle nil as 0."
  [n]
  (let [n (or n 0)]
    (->> (range n)
         (filter #(or (zero? (mod % 11))
                      (zero? (mod % 13))))
         (mapcat str)
         (filter #(= \7 %))
         count)))