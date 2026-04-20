(defn count-distinct-pairs
  "Return the number of unordered pairs of distinct values in a collection.

  The input may contain duplicates and nil values. Only non-nil distinct values
  are counted. For example:
  (count-distinct-pairs [1 2 3]) => 3
  (count-distinct-pairs [1 1 2 2]) => 1
  (count-distinct-pairs [1 nil 2 2 nil 3]) => 3
  (count-distinct-pairs []) => 0"
  [coll]
  (let [n (count (remove nil? (distinct coll)))]
    (quot (* n (dec n)) 2)))