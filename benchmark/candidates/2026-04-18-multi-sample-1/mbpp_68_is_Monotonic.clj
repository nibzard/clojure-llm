(defn is_Monotonic
  "	Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (let [v (vec A)]
    (or (<= (count v) 2)
        (let [pairs (partition 2 1 v)
              nondecreasing? (every? (fn [[x y]] (<= x y)) pairs)
              nonincreasing? (every? (fn [[x y]] (>= x y)) pairs)]
          (or nondecreasing? nonincreasing?)))))