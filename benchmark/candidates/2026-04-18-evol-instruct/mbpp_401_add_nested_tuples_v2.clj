(defn merge-nested-maps
  "Merge two nested maps by adding numeric values at matching paths.

  Non-overlapping keys are kept as-is. If both values are maps, merge them recursively.
  If both values are numbers, add them.
  If one side is nil, treat it as 0 when the other side is numeric.

  Examples:
  (merge-nested-maps {:a 1 :b {:c 2}} {:a 3 :b {:d 4}})
  => {:a 4, :b {:c 2, :d 4}}

  (merge-nested-maps {:a {:x 1}} {:a {:x 5 :y 2}})
  => {:a {:x 6, :y 2}}"
  [m1 m2]
  (letfn [(mergev [a b]
            (cond
              (and (map? a) (map? b)) (merge-with mergev a b)
              (and (number? a) (number? b)) (+ a b)
              (nil? a) b
              (nil? b) a
              :else b))]
    (merge-with mergev m1 m2)))