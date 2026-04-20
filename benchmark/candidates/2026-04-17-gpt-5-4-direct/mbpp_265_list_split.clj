(defn list_split
  "	Write a function that takes in a list and an integer n and splits a list for every nth element, returning a list of the resulting lists."
  [S step]
  (let [step (when (number? step) (long step))]
    (cond
      (nil? S) '()
      (or (nil? step) (<= step 0)) (list S)
      (empty? S) '()
      :else
      (loop [xs (seq S)
             current []
             remaining step
             acc []]
        (if-let [s xs]
          (let [x (first s)
                current' (conj current x)
                remaining' (dec remaining)]
            (if (zero? remaining')
              (recur (next s) [] step (conj acc current'))
              (recur (next s) current' remaining' acc)))
          (let [acc' (if (seq current) (conj acc current) acc)]
            (apply list (map #(apply list %) acc')))))))