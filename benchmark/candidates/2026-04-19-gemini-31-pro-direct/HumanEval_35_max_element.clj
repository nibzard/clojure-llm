(defn max_element
  "Return maximum element in the list."
  [l]
  (when (seq l)
    (apply max l)))