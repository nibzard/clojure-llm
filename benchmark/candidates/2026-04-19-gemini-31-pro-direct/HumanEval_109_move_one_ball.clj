(defn move_one_ball [arr]
  (if (empty? arr)
    true
    (let [pairs (map vector arr (concat (rest arr) [(first arr)]))
          drops (count (filter (fn [[a b]] (> a b)) pairs))]
      (<= drops 1))))