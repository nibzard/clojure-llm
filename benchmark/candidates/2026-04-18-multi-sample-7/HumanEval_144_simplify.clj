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
  (letfn [(parse-frac [s]
            (let [[num den] (map #(Long/parseLong %) (clojure.string/split s #"/"))]
              [num den]))
          (gcd [a b]
            (if (zero? b) a (recur b (mod a b))))]
    (let [[x-num x-den] (parse-frac x)
          [n-num n-den] (parse-frac n)
          num (* x-num n-num)
          den (* x-den n-den)
          d   (gcd num den)]
      (= 1 (quot den d)))))