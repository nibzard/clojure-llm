(defn even_position
  "	Write a cljthon function to check whether every even index contains even numbers of a given list."
  [nums]
  (every? even? (keep-indexed (fn [idx n] (when (even? idx) n)) nums)))