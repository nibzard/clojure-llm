(defn k_smallest_pairs
  "Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [v1 (vec nums1)
        v2 (vec nums2)
        n1 (count v1)
        n2 (count v2)]
    (if (or (zero? n1) (zero? n2) (<= k 0))
      []
      (let [k (min k (* n1 n2))
            ;; Priority queue stores [sum i j]
            ;; We use a sorted-set to simulate a PQ with removal
            pq (transient (sorted-set))
            enqueue (fn [pq item]
                      (conj pq item))
            dequeue (fn [pq]
                      (let [item (first pq)]
                        [item (disj pq item)]))]
        ;; Initialize with pairs (nums1[i], nums2[0])
        (loop [i 0
               pq (reduce-kv (fn [q idx _]
                               (conj q [(+ (v1 idx) (v2 0)) idx 0]))
                             pq
                             v1)
               result []]
          (if (or (>= (count result) k) (empty? pq))
            (persistent result)
            (let [[_ i j] (first pq)
                  pq (disj pq [_ i j])
                  result (conj! result [(v1 i) (v2 j)])]
              (if (< (inc j) n2)
                (recur i (conj pq [(+ (v1 i) (v2 (inc j))) i (inc j)]) result)
                (recur i pq result)))))))))