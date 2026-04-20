(defn is_simple_power
  [x n]
  (cond
    (and (= x 1) (not= n 1)) true
    (or (<= x 0) (<= n 0) (= n 1)) false
    :else
    (loop [p 1]
      (cond
        (= p x) true
        (> p x) false
        :else (recur (* p n))))))