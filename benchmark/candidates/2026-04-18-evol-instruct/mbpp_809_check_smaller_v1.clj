(defn check-longer
  "Return true if every corresponding element of the second vector is longer than the first vector.
  Compare elements as strings by their printed representation length.

  Examples:
  (check-longer [1 22 \"a\"] [10 333 \"zz\"]) => true
  (check-longer [1 22] [10]) => false
  (check-longer [] []) => true"
  [xs ys]
  (and (= (count xs) (count ys))
       (every? true?
               (map (fn [a b]
                      (< (count (str a)) (count (str b))))
                    xs ys))))