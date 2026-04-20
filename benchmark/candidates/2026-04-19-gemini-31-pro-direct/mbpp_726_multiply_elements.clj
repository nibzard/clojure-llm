(defn multiply_elements
  "Write a function that takes as input a list of numbers (t_1,...,t_{N+1}) and returns a list of length N where the i-th element of the vector is equal to t_i * t_{i+1}."
  [test_tup]
  (map * test_tup (rest test_tup)))