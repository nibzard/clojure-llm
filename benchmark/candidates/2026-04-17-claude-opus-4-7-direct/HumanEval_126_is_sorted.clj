(defn is_sorted
  "Given a list of numbers, return whether or not they are sorted
  in ascending order. If list has more than 1 duplicate of the same
  number, return false. Assume no negative numbers and only integers."
  [lst]
  (and (apply <= lst)
       (every? #(<= (count %) 2) (vals (frequencies lst)))))