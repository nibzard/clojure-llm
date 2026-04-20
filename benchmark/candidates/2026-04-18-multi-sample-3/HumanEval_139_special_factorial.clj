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
  (let [[_ result]
        (reduce (fn [[fact acc] i]
                  (let [next-fact (* fact i)]
                    [next-fact (* acc next-fact)]))
                [1N 1N]
                (range 1 (inc n)))]
    result))