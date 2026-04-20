(defn list_split
  "	Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (when (seq S)
    (loop [xs S
           current '()
           count 0
           result '()]
      (if (seq xs)
        (let [next-current (conj current (first xs))
              next-count (inc count)]
          (if (= next-count step)
            (recur (rest xs) '() 0 (conj result (reverse next-current)))
            (recur (rest xs) next-current next-count result)))
        (reverse
         (if (seq current)
           (conj result (reverse current))
           result))))))