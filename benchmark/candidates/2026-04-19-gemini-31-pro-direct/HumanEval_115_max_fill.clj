(defn max_fill [grid capacity]
  (reduce + (map #(quot (+ (reduce + %) capacity -1) capacity) grid)))