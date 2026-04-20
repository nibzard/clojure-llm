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
      (let [cmp-entry (fn [[s1 i1 j1] [s2 i2 j2]]
                        (let [c (compare s1 s2)]
                          (if (zero? c)
                            (let [c2 (compare i1 i2)]
                              (if (zero? c2)
                                (compare j1 j2)
                                c2))
                            c)))
            initial-size (min k n1)
            initial-heap (into (sorted-set-by cmp-entry)
                               (map (fn [i] [(+ (v1 i) (v2 0)) i 0])
                                    (range initial-size)))]
        (loop [heap initial-heap
               result []]
          (if (or (zero? (count heap)) (= (count result) k))
            result
            (let [[_ i j :as entry] (first heap)
                  heap' (disj heap entry)
                  result' (conj result [(v1 i) (v2 j)])]
              (if (< (inc j) n2)
                (recur (conj heap' [(+ (v1 i) (v2 (inc j))) i (inc j)]) result')
                (recur heap' result'))))))))