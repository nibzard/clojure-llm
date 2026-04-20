(defn special_factorial
  "The Brazilian factorial is defined as:
  brazilian_factorial(n) = n! * (n-1)! * (n-2)! * ... * 1!
  where n > 0
  For example:
  >>> (special_factorial 4)
  288
  The function will receive an integer as input and should return the special
  factorial of this integer."
  [n]
  (loop [i 1
         fact 1N
         result 1N]
    (if (> i n)
      result
      (let [next-fact (* fact i)]
        (recur (inc i) next-fact (* result next-fact))))))