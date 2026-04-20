(defn find_dissimilar
  "	Write a function to find the dissimilar elements in the given two vectors."
  [test_tup1 test_tup2]
  (vec
   (concat
    (filter #(not (some #{%} test_tup2)) test_tup1)
    (filter #(not (some #{%} test_tup1)) test_tup2))))