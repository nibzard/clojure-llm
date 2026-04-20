(defn k_smallest_pairs
  "Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [v1 (vec nums1)
        v2 (vec nums2)
        n1 (count v1)
        n2 (count v2)]
    (if (or (zero? n1) (zero? n2) (<= k 0))
      []
      (let [limit (min k (* n1 n2))
            initial-pq (into (sorted-set)
                             (for [i (range n1)]
                               [(+ (v1 i) (v2 0)) i 0]))]
        (loop [pq initial-pq
               result (transient [])]
          (if (or (>= (count result) limit) (empty? pq))
            (persistent! result)
            (let [[sum i j] (first pq)
                  pq' (disj pq [sum i j])
                  result' (conj! result [(v1 i) (v2 j)])]
              (if (< (inc j) n2)
                (recur (conj pq' [(+ (v1 i) (v2 (inc j))) i (inc j)]) result')
                (recur pq' result')))))))))