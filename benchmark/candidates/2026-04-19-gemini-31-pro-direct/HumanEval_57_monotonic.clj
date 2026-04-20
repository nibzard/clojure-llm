(defn monotonic
  "Return true is list elements are monotonically increasing or decreasing."
  [l]
  (or (empty? l)
      (every? identity (map <= l (rest l)))
      (every? identity (map >= l (rest l)))))