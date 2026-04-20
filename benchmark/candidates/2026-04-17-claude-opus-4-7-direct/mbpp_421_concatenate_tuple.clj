(defn concatenate_tuple
  "Write a function to concatenate each element of vector by the delimiter."
  [test_tup]
  (if (empty? test_tup)
    ""
    (clojure.string/join test_tup)))