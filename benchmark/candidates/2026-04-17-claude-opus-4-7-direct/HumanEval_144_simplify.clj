(defn simplify
  "Your task is to implement a function that will simplify the expression
  x * n. The function returns true if x * n evaluates to a whole number and false
  otherwise. Both x and n, are string representation of a fraction, and have the following format,
  <numerator>/<denominator> where both numerator and denominator are positive whole numbers.
  You can assume that x, and n are valid fractions, and do not have zero as denominator.
  >>> (simplify \"1/5\" \"5/1\")
  true
  >>> (simplify \"1/6\" \"2/1\")
  false
  >>> (simplify \"7/10\" \"10/2\")
  false"
  [x n]
  (letfn [(parse-fraction [s]
            (let [[_ num-str den-str] (re-matches #"(\d+)/(\d+)" s)]
              [(Long/parseLong num-str) (Long/parseLong den-str)]))
          (gcd [a b]
            (if (zero? b)
              a
              (recur b (mod a b))))]
    (let [[x-num x-den] (parse-fraction x)
          [n-num n-den] (parse-fraction n)
          ;; Product is (x-num * n-num) / (x-den * n-den)
          num (* x-num n-num)
          den (* x-den n-den)
          d (gcd num den)]
      (zero? (mod den d)))))