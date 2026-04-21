(defn sum_in_range [l r]
  (reduce + (filter odd? (range l (inc r)))))