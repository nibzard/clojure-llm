(defn will_it_fly [q w]
  (and (= q (reverse q))
       (<= (reduce + 0 q) w)))