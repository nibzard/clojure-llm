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
  (let [idx (keep-indexed (fn [i [a b]] (when (<= b a) (inc i)))
                          (partition 2 1 arr))]
    (if (seq idx) (apply max idx) -1)))