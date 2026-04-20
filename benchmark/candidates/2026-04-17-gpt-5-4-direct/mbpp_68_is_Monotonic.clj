(defn is_Monotonic
  "	Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (let [pairs (partition 2 1 A)]
    (or
      (every? (fn [[x y]] (<= x y)) pairs)
      (every? (fn [[x y]] (>= x y)) pairs))))