(defn find_dissimilar
  "	Write a function to find the dissimilar elements in the given two vectors."
  [test_tup1 test_tup2]
  (let [s1 (set (or test_tup1 []))
        s2 (set (or test_tup2 []))]
    (vec (concat (remove s2 test_tup1)
                 (remove s1 test_tup2)))))