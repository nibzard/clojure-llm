(defn solution
  "Given a non-empty list of integers, return the sum of all of the odd elements that are in even positions."
  [lst]
  (->> lst
       (take-nth 2)
       (filter odd?)
       (reduce + 0)))