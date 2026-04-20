(defn smallest_change
  [arr]
  (let [v (vec arr)
        n (count v)]
    (->> (range (quot n 2))
         (filter #(not= (v %) (v (- n 1 %))))
         count)))