(defn odd_position
  "Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every?
   odd?
   (map second
        (filter (fn [[idx _]] (odd? idx))
                (map-indexed vector (or nums []))))))