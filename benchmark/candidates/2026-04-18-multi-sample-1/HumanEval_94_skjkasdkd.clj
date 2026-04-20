(defn skjkasdkd
  "You are given a list of integers.
  You need to find the largest prime value and return the sum of its digits.
  Examples:
  >>> (skjkasdkd [0 3 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3])
  10
  >>> (skjkasdkd [1 0 1 8 2 4597 2 1 3 40 1 2 1 2 4 2 5 1])
  25
  >>> (skjkasdkd [1 3 1 32 5107 34 83278 109 163 23 2323 32 30 1 9 3])
  13
  >>> (skjkasdkd [0 724 32 71 99 32 6 0 5 91 83 0 5 6])
  11
  >>> (skjkasdkd [0 81 12 3 1 21])
  3
  >>> (skjkasdkd [0 8 1 2 1 7])
  7"
  [lst]
  (letfn [(prime? [n]
            (and (integer? n)
                 (> n 1)
                 (or (= n 2)
                     (and (odd? n)
                          (not-any? #(zero? (mod n %))
                                    (range 3 (inc (long (Math/sqrt n))) 2))))))
          (digit-sum [n]
            (->> (str (Math/abs (long n)))
                 (map #(- (int %) (int \0)))
                 (reduce + 0)))]
    (if-let [p (some->> lst (filter prime?) (apply max))]
      (digit-sum p)
      0)))