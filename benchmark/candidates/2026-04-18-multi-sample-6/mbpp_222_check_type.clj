(defn check_type
  "	Write a function to check if all the elements in vector have same data type or not."
  [test_tuple]
  (let [types (->> test_tuple
                   (map #(some-> % class))
                   set)]
    (<= (count types) 1)))