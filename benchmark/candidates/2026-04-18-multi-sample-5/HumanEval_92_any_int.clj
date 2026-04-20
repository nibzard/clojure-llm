(defn any_int
  "Create a function that takes 3 numbers.
  Returns true if one of the numbers is equal to the sum of the other two, and all numbers are integers.
  Returns false in any other cases.
  Examples
  >>> (any_int 5 2 7)
  true
  >>> (any_int 3 2 2)
  false
  >>> (any_int 3 -2 1)
  true
  >>> (any_int 3.6 -2.2 2)
  false"
  [x y z]
  (and (integer? x)
       (integer? y)
       (integer? z)
       (or (= x (+ y z))
           (= y (+ x z))
           (= z (+ x y)))))