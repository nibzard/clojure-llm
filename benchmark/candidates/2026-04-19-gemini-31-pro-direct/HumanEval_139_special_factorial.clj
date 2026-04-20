(defn special_factorial
  "The Brazilian factorial is defined as:
  brazilian_factorial(n) = n! * (n-1)! * (n-2)! * ... * 1!
  where n > 0"
  [n]
  (apply *' (reductions *' (range 1 (inc n)))))