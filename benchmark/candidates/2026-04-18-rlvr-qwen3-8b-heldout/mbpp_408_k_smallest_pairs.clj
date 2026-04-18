(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [pairs (for [a nums1
                    b nums2]
                [a b])]
    (->> pairs
         (sort-by (fn [[x y]] (+ x y)))
         (take k))))