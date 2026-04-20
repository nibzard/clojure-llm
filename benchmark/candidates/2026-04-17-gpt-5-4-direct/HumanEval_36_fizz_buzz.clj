(defn fizz_buzz
  "Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13.
  >>> (fizz_buzz 50)
  0
  >>> (fizz_buzz 78)
  2
  >>> (fizz_buzz 79)
  3"
  [n]
  (let [limit (or n 0)]
    (if (<= limit 0)
      0
      (transduce
       (filter #(or (zero? (mod % 11)) (zero? (mod % 13))))
       (completing
        (fn [acc x]
          (+ acc (count (filter #{\7} (str x))))))
       0
       (range limit)))))