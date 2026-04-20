(defn min-rotations-to-repeat
  "Return the smallest positive rotation count needed to make a vector equal to itself again after circular rotation.
  If the vector has no smaller repeating rotation, return its length.

  Examples:
  (min-rotations-to-repeat [1 2 1 2]) => 2
  (min-rotations-to-repeat [:a :b :c]) => 3
  (min-rotations-to-repeat []) => 0"
  [v])