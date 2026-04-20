(defn next_multiple_of_3
  "Write a Clojure function to find the smallest multiple of 3 greater than or equal to n.

  Examples:
  (next_multiple_of_3 1) => 3
  (next_multiple_of_3 3) => 3
  (next_multiple_of_3 10) => 12

  The function should work with negative numbers and zero as well."
  [n]
  (* 3 (long (Math/ceil (/ n 3.0)))))