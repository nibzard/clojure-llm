(defn add_nested_maps
  "Perform key-wise addition of two nested maps.

  Both inputs are maps whose values may be numbers or nested maps.
  Numeric values at matching paths are added together.
  If a key appears in only one map, its value is kept as-is.

  Examples:
  (add_nested_maps {:a 1 :b {:c 2}} {:a 3 :b {:c 5 :d 7} :e 9})
  => {:a 4 :b {:c 7 :d 7} :e 9}

  (add_nested_maps nil {:x 4})
  => {:x 4}"
  [m1 m2]
  (letfn [(merge-values [v1 v2]
            (cond
              (and (map? v1) (map? v2)) (merge-with merge-values v1 v2)
              (number? v1) (+ v1 (if (number? v2) v2 0))
              (number? v2) (+ v2 (if (number? v1) v1 0))
              (nil? v1) v2
              :else v1))]
    (merge-with merge-values (or m1 {}) (or m2 {}))))