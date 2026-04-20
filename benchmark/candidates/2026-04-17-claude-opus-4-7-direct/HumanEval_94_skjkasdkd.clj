(defn skjkasdkd
  "You are given a list of integers.
  You need to find the largest prime value and return the sum of its digits."
  [lst]
  (letfn [(prime? [n]
            (or (= n 2)
                (and (> n 2)
                     (odd? n)
                     (not (some (fn [d] (zero? (rem n d)))
                                (range 3 (inc (Math/sqrt n)) 2))))))
          (sum-digits [n]
            (reduce + (map #(Character/digit % 10) (str n))))]
    (if-let [max-prime (apply max (filter prime? lst))]
      (sum-digits max-prime)
      0)))