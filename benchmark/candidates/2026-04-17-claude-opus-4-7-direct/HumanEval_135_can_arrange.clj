(defn can_arrange
  "Create a function which returns the largest index of an element which
  is not greater than or equal to the element immediately preceding it. If
  no such element exists then return -1. The given vector will not contain
  duplicate values."
  [arr]
  (or (->> (map vector (range 1 (count arr)) arr)
           (filter (fn [[idx val]] (< val (arr (dec idx)))))
           (map first)
           (last))
      -1))