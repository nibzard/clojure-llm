(defn find_length
  "Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (let [vals (map #(if (= % \0) 1 -1) string)]
    (loop [xs vals
           current 0
           best 0]
      (if (empty? xs)
        best
        (let [current' (max 0 (+ current (first xs)))]
          (recur (rest xs) current' (max best current')))))))