(defn tuple_to_dict
  "	Write a function to convert the given vector to a key-value map using adjacent elements. https://www.geeksforgeeks.org/cljthon-convert-vector-to-adjacent-pair-map/"
  [test_tup]
  (->> test_tup
       (partition 2)
       (map (fn [[k v]] [k v]))
       (into {})))