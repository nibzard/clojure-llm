(defn all-greater?
  "Return true if every element of the vector is greater than the threshold.
  Treat nil as an empty vector. Examples:
  (all-greater? [5 6 7] 4) => true
  (all-greater? [5 3 7] 4) => false
  (all-greater? nil 10) => true"
  [arr threshold])