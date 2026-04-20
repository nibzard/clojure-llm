(defn is_sorted
  "Given a list of numbers, return whether or not they are sorted
  in ascending order. If list has more than 1 duplicate of the same
  number, return false. Assume no negative numbers and only integers."
  [lst]
  (let [pairs (partition 2 1 lst)]
    (and
      (every? (fn [[a b]] (<= a b)) pairs)
      (every? (fn [[_ b]] (not= 0 (count (filter #(= b %) lst))))
              pairs)
      (every? (fn [x] (<= (count (filter #(= x %) lst)) 2)) lst))))