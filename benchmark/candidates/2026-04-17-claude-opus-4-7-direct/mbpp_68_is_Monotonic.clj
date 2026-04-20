(defn is_Monotonic
  "Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (or (apply <= A)
      (apply >= A)))