(defn unique-Element
  "Check whether a list of numbers contains only one distinct element or not."
  [arr]
  (if (empty? arr)
    false
    (= 1 (count (set arr)))))