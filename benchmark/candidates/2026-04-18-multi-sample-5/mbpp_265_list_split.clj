(defn list_split
  "	Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (cond
    (nil? S) '()
    (or (nil? step) (<= step 0)) (list S)
    :else
    (->> S
         (partition-all step)
         (map #(apply list %))
         (apply list))))