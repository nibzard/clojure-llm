(defn combinations_list
  "	Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (let [v (vec (or list1 []))
        n (count v)]
    (->> (range 1 (bit-shift-left 1 n))
         (map (fn [mask]
                (->> (range n)
                     (keep (fn [i]
                             (when (not (zero? (bit-and mask (bit-shift-left 1 i))))
                               (v i))))
                     vec)))
         vec)))