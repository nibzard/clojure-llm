(defn find
  "Write a cljthon function to find quotient of two numbers (rounded down to the nearest integer)."
  [n m]
  (if (zero? m)
    nil
    (long (Math/floor (/ n m)))))