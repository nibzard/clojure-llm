(defn k_smallest_pairs
  [nums1 nums2 k]
  (->> (for [x nums1 y nums2] [x y])
       (sort-by #(+ (first %) (second %)))
       (take k)))