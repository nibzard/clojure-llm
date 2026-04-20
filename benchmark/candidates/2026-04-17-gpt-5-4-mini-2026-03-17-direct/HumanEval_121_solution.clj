(defn solution
  [lst]
  (reduce-kv (fn [sum idx x]
               (if (and (even? idx) (odd? x))
                 (+ sum x)
                 sum))
             0
             lst))