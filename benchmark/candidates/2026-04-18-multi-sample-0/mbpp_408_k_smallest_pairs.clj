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
      (let [cmp (fn [[s1 _ _] [s2 _ _]] (compare s1 s2))
            initial-count (min k n1)
            initial-heap (into clojure.lang.PersistentQueue/EMPTY
                               (sort cmp
                                     (map (fn [i]
                                            [(+ (v1 i) (v2 0)) i 0])
                                          (range initial-count))))]
        (loop [heap initial-heap
               result []
               remaining k]
          (if (or (zero? remaining) (empty? heap))
            result
            (let [[_ i j :as top] (peek heap)
                  heap-rest (pop heap)
                  result' (conj result [(v1 i) (v2 j)])
                  heap' (if (< (inc j) n2)
                          (let [next-item [(+ (v1 i) (v2 (inc j))) i (inc j)]]
                            (into clojure.lang.PersistentQueue/EMPTY
                                  (sort cmp (conj (vec heap-rest) next-item))))
                          heap-rest)]
              (recur heap' result' (dec remaining))))))))