(defn get_row [lst x]
  (vec (sort-by (fn [[r c]] [r (- c)])
                (for [[r row] (map-indexed vector lst)
                      [c val] (map-indexed vector row)
                      :when (= val x)]
                  [r c]))))