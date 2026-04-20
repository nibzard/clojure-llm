(defn union_elements
  "	Write a function to find the union of the elements of two given lists and output them in sorted order."
  [test_tup1 test_tup2]
  (->> (concat (or test_tup1 []) (or test_tup2 []))
       set
       sort
       vec))