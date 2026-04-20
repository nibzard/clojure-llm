(defn tuple_modulo
  "Write a function which takes two vectors of the same length and performs the element wise modulo."
  [test_tup1 test_tup2]
  (mapv mod test_tup1 test_tup2))