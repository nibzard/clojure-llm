(defn median-of-coll
  "Return the median value of a collection of numbers.

  The collection may contain any number of numeric values, including nils.
  Ignore nil values. If there are no non-nil values, return nil.

  Examples:
  (median-of-coll [3 1 2]) => 2
  (median-of-coll [10 nil 4 7]) => 7
  (median-of-coll []) => nil"
  [xs]
  (let [nums (sort (remove nil? xs))
        n (count nums)]
    (when (pos? n)
      (nth nums (quot (dec n) 2)))))