(defn freq-count-sorted
  "Return a map of element frequencies for a collection, but ignore nil values and sort by descending frequency in the resulting map's iteration order.

Examples:
  (freq-count-sorted [:a :b :a nil :c :b :a])
  ;; => {:a 3, :b 2, :c 1}

  (freq-count-sorted [])
  ;; => {}"
  [coll]
  (into (sorted-map-by (fn [[k1 v1] [k2 v2]]
                         (compare [(- v2) k1] [(- v1) k2])))
        (frequencies (remove nil? coll))))