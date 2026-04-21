(defn test_three_equal
  "Write a cljthon function to count the number of equal numbers from three given integers."
  [x y z]
  (cond
    (= x y z) 3
    (or (= x y) (= y z) (= x z)) 2
    :else 0))