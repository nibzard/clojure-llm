(defn any_int
  "Create a function that takes 3 numbers.
  Returns true if one of the numbers is equal to the sum of the other two, and all numbers are integers.
  Returns false in any other cases."
  [x y z]
  (and (every? integer? [x y z])
       (or (= x (+ y z))
           (= y (+ x z))
           (= z (+ x y)))))