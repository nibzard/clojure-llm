(defn list_split
  "Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (if (or (nil? S) (<= step 0))
    '()
    (loop [remaining S
           result '()]
      (if (empty? remaining)
        (reverse result)
        (recur (drop step remaining)
               (conj result (take step remaining)))))))