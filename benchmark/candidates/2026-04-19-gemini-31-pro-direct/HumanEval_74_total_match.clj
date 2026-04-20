(defn total_match [lst1 lst2]
  (let [c1 (apply + (map count lst1))
        c2 (apply + (map count lst2))]
    (if (<= c1 c2)
      lst1
      lst2)))