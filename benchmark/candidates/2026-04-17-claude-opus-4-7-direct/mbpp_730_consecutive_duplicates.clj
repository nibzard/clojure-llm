(defn consecutive_duplicates
  "Write a function to remove consecutive duplicates of a given list."
  [nums]
  (when (seq nums)
    (reduce (fn [acc x]
              (if (= (first acc) x)
                acc
                (cons x acc)))
            (list (first nums))
            (rest nums))))