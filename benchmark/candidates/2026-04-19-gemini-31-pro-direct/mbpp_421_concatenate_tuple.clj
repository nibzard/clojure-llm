(defn concatenate_tuple
  "	Write a function to concatenate each element of vector by the delimiter."
  ([test_tup]
   (apply str test_tup))
  ([test_tup delim]
   (clojure.string/join delim test_tup)))