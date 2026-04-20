(defn find
  "Write a cljthon function to find quotient of two numbers (rounded down to the nearest integer)."
  [n m]
  (long (Math/floor (/ n m))))