(defn monotonic
  "Return true is list elements are monotonically increasing or decreasing."
  [l]
  (or (apply <= l)
      (apply >= l)))