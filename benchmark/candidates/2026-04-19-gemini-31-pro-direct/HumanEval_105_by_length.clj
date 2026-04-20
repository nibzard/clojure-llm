(defn by_length [arr]
  (let [names ["Zero" "One" "Two" "Three" "Four" "Five" "Six" "Seven" "Eight" "Nine"]]
    (->> arr
         (filter #(and (>= % 1) (<= % 9)))
         (sort >)
         (mapv names))))