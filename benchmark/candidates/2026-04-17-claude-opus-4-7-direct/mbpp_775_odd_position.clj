(defn odd_position
  "Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every? (fn [[i n]] (or (even? i) (odd? n)))
          (map vector (range) nums)))