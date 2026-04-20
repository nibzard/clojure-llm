(defn can_arrange
  "Create a function which returns the largest index of an element which
  is not greater than or equal to the element immediately preceding it. If
  no such element exists then return -1. The given vector will not contain
  duplicate values.
  Examples:
  >>> (can_arrange [1 2 4 3 5])
  3
  >>> (can_arrange [1 2 3])
  -1"
  [arr]
  (or
   (first
    (keep (fn [i]
            (when (<= (nth arr i) (nth arr (dec i)))
              i))
          (range (dec (count arr)) 0 -1)))
   -1))