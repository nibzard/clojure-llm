(defn below_threshold
  "Return true if all numbers in the list l are below threshold t."
  [l t]
  (every? #(< % t) l))