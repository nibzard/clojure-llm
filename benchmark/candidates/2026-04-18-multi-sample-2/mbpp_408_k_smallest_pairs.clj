(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [v1 (vec (or nums1 []))
        v2 (vec (or nums2 []))
        n1 (count v1)
        n2 (count v2)
        k  (max 0 (long (or k 0)))]
    (if (or (zero? k) (zero? n1) (zero? n2))
      []
      (letfn [(make-entry [i j]
                [(+ (v1 i) (v2 j)) i j])
              (heap-push [heap entry]
                (let [cmp (fn [[s1 i1 j1] [s2 i2 j2]]
                            (or (< s1 s2)
                                (and (= s1 s2)
                                     (or (< i1 i2)
                                         (and (= i1 i2) (< j1 j2))))))]
                  (loop [h (conj heap entry)
                         idx (dec (count (conj heap entry)))]
                    (if (zero? idx)
                      h
                      (let [parent (quot (dec idx) 2)]
                        (if (cmp (h idx) (h parent))
                          (recur (-> h
                                     (assoc idx (h parent))
                                     (assoc parent (h idx)))
                                 parent)
                          h))))))
              (heap-pop [heap]
                (let [n (count heap)]
                  (cond
                    (zero? n) [nil []]
                    (= n 1) [(heap 0) []]
                    :else
                    (let [root (heap 0)
                          last-entry (heap (dec n))
                          h (assoc (pop heap) 0 last-entry)
                          cmp (fn [[s1 i1 j1] [s2 i2 j2]]
                                (or (< s1 s2)
                                    (and (= s1 s2)
                                         (or (< i1 i2)
                                             (and (= i1 i2) (< j1 j2))))))]
                      (loop [h h
                             idx 0]
                        (let [left (inc (* 2 idx))
                              right (+ 2 (* 2 idx))
                              size (count h)]
                          (if (>= left size)
                            [root h]
                            (let [smallest-child (if (and (< right size)
                                                          (cmp (h right) (h left)))
                                                   right
                                                   left)]
                              (if (cmp (h smallest-child) (h idx))
                                (recur (-> h
                                           (assoc idx (h smallest-child))
                                           (assoc smallest-child (h idx)))
                                       smallest-child)
                                [root h])))))))))]
        (loop [heap (reduce heap-push [] (map #(make-entry % 0) (range (min n1 k))))
               result []]
          (if (or (zero? (count heap)) (>= (count result) k))
            result
            (let [[[ _ i j] heap'] (heap-pop heap)
                  result' (conj result [(v1 i) (v2 j)])
                  heap'' (if (< (inc j) n2)
                           (heap-push heap' (make-entry i (inc j)))
                           heap')]
              (recur heap'' result')))))))