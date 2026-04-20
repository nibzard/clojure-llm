(defn count_Occurrence
  "Write a clojure function to count the occurence of all elements of list in a vector."
  [tup lst]
  (count (filter (set lst) tup)))