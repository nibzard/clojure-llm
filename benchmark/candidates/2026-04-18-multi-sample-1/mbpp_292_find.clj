(defn find
  "	Write a cljthon function to find quotient of two numbers (rounded down to the nearest integer)."
  [n m]
  (when (and (number? n) (number? m) (not (zero? m)))
    (long (Math/floor (/ (double n) (double m))))))