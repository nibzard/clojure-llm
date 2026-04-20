(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [n1 (vec nums1)
        n2 (vec nums2)
        len1 (count n1)
        len2 (count n2)]
    (if (or (nil? k) (<= k 0) (zero? len1) (zero? len2))
      []
      (let [cmp (fn [[s1 i1 j1] [s2 i2 j2]]
                  (let [c (compare s1 s2)]
                    (if (zero? c)
                      (let [c2 (compare i1 i2)]
                        (if (zero? c2) (compare j1 j2) c2))
                      c)))
            init (into (sorted-set-by cmp)
                       (for [i (range (min len1 k))]
                         [(+ (nth n1 i) (nth n2 0)) i 0]))]
        (loop [pq init
               res []]
          (if (or (empty? pq) (>= (count res) k))
            res
            (let [[[sum i j] & more] pq
                  pq' (disj pq [sum i j])
                  res' (conj res [(nth n1 i) (nth n2 j)])
                  pq'' (if (< (inc j) len2)
                         (conj pq' [(+ (nth n1 i) (nth n2 (inc j))) i (inc j)])
                         pq')]
              (recur pq'' res')))))))