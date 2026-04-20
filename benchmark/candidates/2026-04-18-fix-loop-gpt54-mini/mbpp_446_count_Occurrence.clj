(defn count_Occurrence
  "	Write a cljthon function to count the occurence of all elements of list in a vector."
  [tup lst]
  (vec (map #(count (filter (fn [x] (= x %)) lst)) tup)))