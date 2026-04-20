(defn drop_empty
  "Write a function to drop empty items from a given map."
  [dict1]
  (into {} (remove (fn [[_ v]] (empty? v)) dict1)))