(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [nums1 (or nums1 [])
        nums2 (or nums2 [])
        k (max 0 (long (or k 0)))]
    (->> (for [x nums1
               y nums2]
           [x y])
         (sort-by (fn [[x y]] (+ x y)))
         (take k)
         vec)))