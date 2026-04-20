(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [pairs (for [x nums1
                    y nums2]
                [x y])
        sorted-pairs (sort-by (fn [[a b]] (+ a b)) pairs)]
    (vec (take (max 0 k) sorted-pairs))))