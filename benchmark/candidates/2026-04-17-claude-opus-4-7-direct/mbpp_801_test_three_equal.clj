(defn test_three_equal
  "Count the number of equal numbers from three given integers."
  [x y z]
  (cond
    (= x y z) 3
    (or (= x y) (= x z) (= y z)) 2
    :else 0))