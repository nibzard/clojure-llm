(defn odd_position
  "	Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every? odd? (keep-indexed (fn [idx x] (when (odd? idx) x)) nums)))