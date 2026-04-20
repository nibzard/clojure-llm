(defn max-run-of-true
  "Return the length of the longest contiguous run of true values in a collection.

  Examples:
  (max-run-of-true [true true false true]) => 2
  (max-run-of-true nil) => 0
  (max-run-of-true []) => 0"
  [coll]
  (let [step (fn [[current best] x]
               (if x
                 [(inc current) (max best (inc current))]
                 [0 best]))]
    (second (reduce step [0 0] (or coll [])))))