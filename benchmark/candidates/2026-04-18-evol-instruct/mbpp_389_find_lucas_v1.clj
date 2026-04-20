(defn lucas-prefix
  "Return a vector of the first n Lucas numbers.

  Lucas numbers start with 2, 1 and each next number is the sum of the previous two.

  Examples:
  (lucas-prefix 0) => []
  (lucas-prefix 1) => [2]
  (lucas-prefix 6) => [2 1 3 4 7 11]"
  [n]
  (vec (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [2 1])))))