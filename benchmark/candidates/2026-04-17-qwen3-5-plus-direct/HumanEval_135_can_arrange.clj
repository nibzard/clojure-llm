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
  (if (or (nil? arr) (< (count arr) 2))
    -1
    (let [indices (keep-indexed 
                   (fn [i val] 
                     (when (and (> i 0) (< val (get arr (dec i))))
                       i))
                   arr)]
      (if (empty? indices)
        -1
        (last indices)))))