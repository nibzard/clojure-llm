(defn large_product
  "	Write a function to find the specified number of largest products from two given lists, selecting one factor from each list."
  [nums1 nums2 N]
  (let [a (seq nums1)
        b (seq nums2)
        n (max 0 (or N 0))]
    (if (or (nil? a) (nil? b) (zero? n))
      '()
      (->> (for [x a
                 y b]
             (* x y))
           sort
           reverse
           (take n)))))