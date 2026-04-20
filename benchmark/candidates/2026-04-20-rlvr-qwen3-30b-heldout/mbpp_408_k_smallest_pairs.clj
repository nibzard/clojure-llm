(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [xs (or nums1 [])
        ys (or nums2 [])
        pairs (for [x xs
                    y ys]
                [x y])]
    (->> pairs
         (sort-by (fn [[x y]] (+ x y)))
         (take (max 0 (min k (count pairs))))
         vec)))