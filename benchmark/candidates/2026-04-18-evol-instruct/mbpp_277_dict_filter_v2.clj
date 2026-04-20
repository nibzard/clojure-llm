(defn dict_filter_lazy
  "Return a lazy sequence of [k v] entries from m whose values are greater than or equal to n.

  The function should work with regular maps and also with potentially infinite or large sequences of map entries.

  Examples:
  (dict_filter_lazy {:a 1 :b 3 :c 2} 2)
  => ([:b 3] [:c 2])

  (dict_filter_lazy (sorted-map :a 1 :b 4 :c 2) 3)
  => ([:b 4])

  The returned value must be lazy and preserve the order of the input entries."
  [m n]
  (filter (fn [[_ v]] (>= v n)) m))