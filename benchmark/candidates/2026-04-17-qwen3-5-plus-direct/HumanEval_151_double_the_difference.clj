(defn double_the_difference [lst]
  (->> lst
       (filter #(and (integer? %)
                     (>= % 0)
                     (odd? %)))
       (map #(* % %))
       (reduce + 0)))