(defn freq-count-sorted
  "Return a map of element frequencies for a collection, but ignore nil values and sort by descending frequency in the resulting map's iteration order.

Examples:
  (freq-count-sorted [:a :b :a nil :c :b :a])
  ;; => {:a 3, :b 2, :c 1}

  (freq-count-sorted [])
  ;; => {}"
  [coll])