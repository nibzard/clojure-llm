(defn nested-map-sum
  "Return the sum of all numeric values in a nested map structure.

  Non-numeric values are ignored. Nested maps are searched recursively.
  Returns 0 for nil or an empty map.

  Examples:
  (nested-map-sum {:a 1 :b 2})
  ;;=> 3

  (nested-map-sum {:a 1 :b {:c 2 :d 3} :e \"x\"})
  ;;=> 6

  (nested-map-sum nil)
  ;;=> 0"
  [m]
  (reduce
   +
   0
   (for [v (vals (or m {}))
         :when (or (number? v) (map? v))]
     (if (map? v)
       (nested-map-sum v)
       v))))