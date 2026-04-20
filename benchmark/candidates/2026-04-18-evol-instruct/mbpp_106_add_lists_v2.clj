(defn merge-maps-deep
  "Merge two maps recursively.

  - If both maps contain the same key and both values are maps, merge them deeply.
  - Otherwise, the value from the second map wins.
  - Treat nil as an empty map.

  Examples:
  (merge-maps-deep {:a 1 :b {:c 2}} {:b {:d 3} :e 4})
  ;; => {:a 1, :b {:c 2, :d 3}, :e 4}

  (merge-maps-deep nil {:x 1})
  ;; => {:x 1}"
  [m1 m2]
  (merge-with
   (fn [v1 v2]
     (if (and (map? v1) (map? v2))
       (merge-maps-deep v1 v2)
       v2))
   (or m1 {})
   (or m2 {})))