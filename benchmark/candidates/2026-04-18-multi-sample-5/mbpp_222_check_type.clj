(defn check_type
  "	Write a function to check if all the elements in vector have same data type or not."
  [test_tuple]
  (or (empty? test_tuple)
      (apply = (map #(if (nil? %) nil (class %)) test_tuple))))