(defn choose_num [x y]
  (cond
    (> x y) -1
    (even? y) y
    (>= (dec y) x) (dec y)
    :else -1))