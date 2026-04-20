(defn odd_position
  "	Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every? (fn [[idx x]]
            (or (even? idx)
                (odd? x)))
          (map-indexed vector nums)))