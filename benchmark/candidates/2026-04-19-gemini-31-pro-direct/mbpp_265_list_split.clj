(defn list_split
  "Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (if (and step (pos? step))
    (partition-all step S)
    (list S)))