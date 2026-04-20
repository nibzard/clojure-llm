(defn find_dissimilar
  "	Write a function to find the dissimilar elements in the given two vectors."
  [test_tup1 test_tup2]
  (vec
   (concat
    (remove (set test_tup2) test_tup1)
    (remove (set test_tup1) test_tup2))))