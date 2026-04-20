(defn odd_position
  "Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every? true?
          (map-indexed (fn [idx n]
                         (if (odd? idx)
                           (odd? n)
                           true))
                       nums)))