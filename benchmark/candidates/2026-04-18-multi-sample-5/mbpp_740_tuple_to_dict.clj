(defn tuple_to_dict
  "	Write a function to convert the given vector to a key-value map using adjacent elements. https://www.geeksforgeeks.org/cljthon-convert-vector-to-adjacent-pair-map/"
  [test_tup]
  (if (seq test_tup)
    (into {}
          (map vec (partition 2 2 nil test_tup)))
    {}))