(defn rolling-max-indexes
  "Given a collection of integers, return a vector of the index of the maximum
  element seen so far at each position.

  If the current value ties the current maximum, keep the earlier index.
  Return an empty vector for an empty input.

  Examples:
  (rolling-max-indexes [1 2 3 2 3 4 2])
  => [0 1 2 2 2 5 5]

  (rolling-max-indexes [5 5 1 6 6])
  => [0 0 0 3 3]"
  [numbers]
  (second
    (reduce (fn [[best-val best-idx out idx] n]
              (let [new-best? (> n best-val)
                    best-val' (if new-best? n best-val)
                    best-idx' (if new-best? idx best-idx)]
                [best-val' best-idx' (conj out best-idx') (inc idx)]))
            [nil nil [] 0]
            numbers)))