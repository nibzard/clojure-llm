(defn k-smallest-sums
  "Return the k smallest sums formed by taking one element from each of the two input vectors.
  
  The result is a vector of [x y sum] triples sorted by sum ascending, then x ascending, then y ascending.
  
  Examples:
  (k-smallest-sums [1 7 11] [2 4 6] 3)
  ;; => [[1 2 3] [1 4 5] [1 6 7]]
  
  If k is nil, return all possible pairs.
  If either input vector is empty, return an empty vector."
  [nums1 nums2 k]
  (let [pairs (for [x nums1
                    y nums2]
                [x y (+ x y)])
        sorted-pairs (sort-by (fn [[x y s]] [s x y]) pairs)]
    (vec (if (nil? k)
           sorted-pairs
           (take k sorted-pairs)))))