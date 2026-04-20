(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [nums1 (vec (or nums1 []))
        nums2 (vec (or nums2 []))
        n1 (count nums1)
        n2 (count nums2)
        k (max 0 (or k 0))]
    (if (or (zero? k) (zero? n1) (zero? n2))
      []
      (letfn [(heapify-up [heap idx]
                (loop [h heap
                       i idx]
                  (if (zero? i)
                    h
                    (let [p (quot (dec i) 2)
                          vi (nth h i)
                          vp (nth h p)]
                      (if (<= (first vi) (first vp))
                        (recur (-> h
                                   (assoc i vp)
                                   (assoc p vi))
                               p)
                        h)))))
              (heapify-down [heap idx]
                (let [cnt (count heap)]
                  (loop [h heap
                         i idx]
                    (let [l (inc (* 2 i))
                          r (+ 2 (* 2 i))]
                      (if (>= l cnt)
                        h
                        (let [smallest-child
                              (if (and (< r cnt)
                                       (< (first (nth h r)) (first (nth h l))))
                                r
                                l)
                              vi (nth h i)
                              vc (nth h smallest-child)]
                          (if (> (first vi) (first vc))
                            (recur (-> h
                                       (assoc i vc)
                                       (assoc smallest-child vi))
                                   smallest-child)
                            h)))))))
              (heap-push [heap item]
                (heapify-up (conj heap item) (count heap)))
              (heap-pop [heap]
                (let [cnt (count heap)]
                  (cond
                    (zero? cnt) [nil []]
                    (= cnt 1) [(first heap) []]
                    :else (let [root (first heap)
                                last-item (peek heap)
                                new-heap (assoc (pop heap) 0 last-item)]
                            [root (heapify-down new-heap 0)]))))]
        (loop [i 0
               heap []]
          (if (< i (min n1 k))
            (recur (inc i)
                   (heap-push heap [(+ (nums1 i) (nums2 0)) i 0]))
            (loop [h heap
                   remaining k
                   result []]
              (if (or (zero? remaining) (empty? h))
                result
                (let [[[ _ i j] h2] (heap-pop h)
                      result2 (conj result [(nums1 i) (nums2 j)])
                      h3 (if (< (inc j) n2)
                           (heap-push h2 [(+ (nums1 i) (nums2 (inc j))) i (inc j)])
                           h2)]
                  (recur h3 (dec remaining) result2)))))))))