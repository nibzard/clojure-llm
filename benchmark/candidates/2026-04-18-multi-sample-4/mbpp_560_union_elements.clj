(defn union_elements
  "	Write a function to find the union of the elements of two given lists and output them in sorted order."
  [test_tup1 test_tup2]
  (sort (clojure.set/union (set (or test_tup1 []))
                           (set (or test_tup2 [])))))