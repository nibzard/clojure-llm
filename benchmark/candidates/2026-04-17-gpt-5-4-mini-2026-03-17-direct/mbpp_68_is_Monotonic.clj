(defn is_Monotonic
  "Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (let [pairs (partition 2 1 A)
        nondecreasing? (every? (fn [[x y]] (<= x y)) pairs)
        nonincreasing? (every? (fn [[x y]] (>= x y)) pairs)]
    (or nondecreasing? nonincreasing?)))