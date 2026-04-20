(defn is_sorted
  [lst]
  (and (every? (fn [[a b]] (<= a b)) (partition 2 1 lst))
       (every? #(<= % 2) (vals (frequencies lst)))))