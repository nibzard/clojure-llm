(defn large_product
  "	Write a function to find the specified number of largest products from two given lists, selecting one factor from each list."
  [nums1 nums2 N]
  (let [xs (seq nums1)
        ys (seq nums2)
        n  (max 0 (or N 0))]
    (if (or (zero? n) (nil? xs) (nil? ys))
      '()
      (let [products (for [x xs
                           y ys]
                       (* x y))]
        (->> products
             (sort >)
             (take n))))))