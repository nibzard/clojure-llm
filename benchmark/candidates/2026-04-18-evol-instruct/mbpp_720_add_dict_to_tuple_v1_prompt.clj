(defn merge-vectors-by-index
  "Merge two vectors by index, using a combining function for each pair.
   If one vector is longer, append the remaining elements unchanged.
   Examples:
   (merge-vectors-by-index [1 2 3] [10 20] +) => [11 22 3]
   (merge-vectors-by-index [1] [10 20 30] str) => [\"110\" 20 30]"
  [v1 v2 combine-fn])