(defn intersection_array
  "	Write a function to find the intersection of two vectors."
  [array_nums1 array_nums2]
  (let [s2 (set array_nums2)]
    (vec
     (distinct
      (filter s2 array_nums1)))))