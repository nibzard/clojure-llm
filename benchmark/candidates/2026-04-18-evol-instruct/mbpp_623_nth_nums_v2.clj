(defn nth-finite-prefix
  "Return the first n elements from seqs that may be infinite, forcing only what is needed.
  Examples:
  (nth-finite-prefix [1 2 3 4] 2) => [1 2]
  (nth-finite-prefix (range) 5) => [0 1 2 3 4]"
  [xs n]
  (vec (take n xs)))