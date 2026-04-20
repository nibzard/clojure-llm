(defn division_elements
  "	Write a function that takes in two vectors and performs mathematical division operation element-wise across the given vectors."
  [test_tup1 test_tup2]
  (vec
   (map
    (fn [a b]
      (if (zero? b)
        nil
        (/ a b)))
    test_tup1
    test_tup2)))