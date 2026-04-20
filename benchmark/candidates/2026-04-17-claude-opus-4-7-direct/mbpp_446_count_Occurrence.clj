(defn count_Occurrence
  "Write a cljthon function to count the occurence of all elements of list in a vector."
  [tup lst]
  (reduce
    (fn [acc item]
      (assoc acc item (inc (get acc item 0))))
    {}
    (filter lst tup)))