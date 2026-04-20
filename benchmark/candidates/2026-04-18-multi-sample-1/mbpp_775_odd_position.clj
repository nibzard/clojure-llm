(defn odd_position
  "	Write a cljthon function to check whether every odd index contains odd numbers of a given list."
  [nums]
  (every?
   (fn [[idx n]])
   (keep-indexed (fn [idx n]
                   (when (odd? idx) [idx n]))
                 nums)))