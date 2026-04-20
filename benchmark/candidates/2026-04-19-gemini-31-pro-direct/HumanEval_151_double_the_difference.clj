(defn double_the_difference
  [lst]
  (->> lst
       (filter integer?)
       (filter pos?)
       (filter odd?)
       (map #(* % %))
       (reduce + 0)))