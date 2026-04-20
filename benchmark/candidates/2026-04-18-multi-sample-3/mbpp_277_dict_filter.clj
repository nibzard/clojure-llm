(defn dict_filter
  "	Write a function that takes in a map and integer n and filters the map to only include entries with values greater than or equal to n."
  [dict n]
  (into {}
        (filter (fn [[_ v]] (and (number? v) (>= v n))))
        dict))