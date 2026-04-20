(defn total_match
  [lst1 lst2]
  (let [total-chars (fn [lst] (reduce + 0 (map count lst)))
        t1 (total-chars lst1)
        t2 (total-chars lst2)]
    (if (<= t1 t2) lst1 lst2)))