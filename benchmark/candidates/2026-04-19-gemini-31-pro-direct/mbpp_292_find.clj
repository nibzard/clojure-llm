(defn find
  "Write a cljthon function to find quotient of two numbers (rounded down to the nearest integer)."
  [n m]
  (int (Math/floor (/ n m))))