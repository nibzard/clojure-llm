(defn intersection_array
  "Write a function to find the intersection of two vectors."
  [array_nums1 array_nums2]
  (let [set1 (set array_nums1)]
    (vec (filter set1 array_nums2))))