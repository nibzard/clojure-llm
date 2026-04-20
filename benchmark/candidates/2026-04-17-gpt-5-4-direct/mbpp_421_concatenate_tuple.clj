(defn concatenate_tuple
  "Write a function to concatenate each element of vector by the delimiter."
  [test_tup]
  (if (seq test_tup)
    (apply str (interpose " " test_tup))
    ""))