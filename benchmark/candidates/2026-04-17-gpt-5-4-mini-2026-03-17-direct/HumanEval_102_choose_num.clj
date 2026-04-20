(defn choose_num
  [x y]
  (let [lo (min x y)
        hi (max x y)
        candidate (if (even? hi) hi (dec hi))]
    (if (>= candidate lo) candidate -1)))