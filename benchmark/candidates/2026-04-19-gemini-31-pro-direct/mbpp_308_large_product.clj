(defn large_product
  "Write a function to find the specified number of largest products from two given lists, selecting one factor from each list."
  [nums1 nums2 N]
  (take N (sort > (for [x nums1
                        y nums2]
                    (* x y)))))