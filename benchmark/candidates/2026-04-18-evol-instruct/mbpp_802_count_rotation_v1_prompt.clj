(defn count-rotation
  "Return the number of left rotations required to turn a sorted vector into the given vector.

  The input vector may contain duplicate values.

  Examples:
  (count-rotation [15 18 2 3 6 12]) => 2
  (count-rotation [7 9 11 12 5]) => 4
  (count-rotation [1 2 3 4]) => 0
  (count-rotation [2 2 2 1 2]) => 3"
  [arr])