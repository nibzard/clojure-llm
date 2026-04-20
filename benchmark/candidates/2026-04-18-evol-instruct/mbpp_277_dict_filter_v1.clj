(defn dict_filter_invert
  "Given a map and a predicate, return a new map containing only the entries whose values do NOT satisfy the predicate.

  Examples:
  (dict_filter_invert {:a 1 :b 2 :c 3} even?)
  => {:a 1 :c 3}

  (dict_filter_invert {:x nil :y 10 :z 15} nil?)
  => {:y 10 :z 15}"
  [m pred]
  (into {} (remove (fn [[_ v]] (pred v)) m)))