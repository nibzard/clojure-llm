(defn pluck
  "Given a vector representing a branch of a tree that has non-negative integer nodes
  your task is to pluck one of the nodes and return it.
  The plucked node should be the node with the smallest even value.
  If multiple nodes with the same smallest even value are found return the node that has smallest index.
  The plucked node should be returned in a list, [ smalest_value, its index ],
  If there are no even values or the given vector is empty, return []."
  [arr]
  (let [result (reduce-kv (fn [best idx v]
                            (if (even? v)
                              (if (or (nil? best) (< v (first best)))
                                [v idx]
                                best)
                              best))
                          nil
                          (or arr []))]
    (or result [])))