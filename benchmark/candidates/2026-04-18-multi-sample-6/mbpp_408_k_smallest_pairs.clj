(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [v1 (vec (or nums1 []))
        v2 (vec (or nums2 []))
        n1 (count v1)
        n2 (count v2)
        k  (max 0 (or k 0))]
    (if (or (zero? k) (zero? n1) (zero? n2))
      []
      (let [cmp (fn [[s1 _ _] [s2 _ _]]
                  (let [c (compare s1 s2)]
                    (if (zero? c) -1 c)))
            initial-count (min k n1)
            initial-heap (reduce
                          (fn [h i]
                            (conj h [(+ (v1 i) (v2 0)) i 0]))
                          (sorted-set-by cmp)
                          (range initial-count))]
        (loop [heap initial-heap
               result []]
          (if (or (zero? (count heap)) (= (count result) k))
            result
            (let [[_ i j :as node] (first heap)
                  heap' (disj heap node)
                  result' (conj result [(v1 i) (v2 j)])
                  heap'' (if (< (inc j) n2)
                           (conj heap' [(+ (v1 i) (v2 (inc j))) i (inc j)])
                           heap')]
              (recur heap'' result')))))))