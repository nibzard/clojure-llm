(defn list_split
  "Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (if (or (nil? S) (nil? step) (<= step 0))
    ()
    (loop [xs (seq S)
           acc []]
      (if (empty? xs)
        acc
        (let [chunk (take step xs)]
          (recur (drop step xs) (conj acc chunk)))))))