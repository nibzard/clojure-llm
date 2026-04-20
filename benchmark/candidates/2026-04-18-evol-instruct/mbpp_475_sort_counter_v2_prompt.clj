(defn sort-by-value-desc
  "Return a vector of [key value] pairs from m sorted by value descending.
  If values are tied, sort by key ascending.

  Examples:
  (sort-by-value-desc {:b 2 :a 1 :c 2})
  ;; => [[:a 1] [:b 2] [:c 2]]

  (sort-by-value-desc nil)
  ;; => []"
  [m])