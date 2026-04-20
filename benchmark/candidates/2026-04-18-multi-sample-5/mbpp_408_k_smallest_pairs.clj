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
                      (if (< (nth vi 0) (nth vp 0))
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
                          r (+ 2 (* 2 i))
                          smallest (cond
                                     (>= l cnt) i
                                     (>= r cnt) (if (< (nth (nth h l) 0) (nth (nth h i) 0)) l i)
                                     :else (let [si (if (< (nth (nth h l) 0) (nth (nth h i) 0)) l i)]
                                             (if (< (nth (nth h r) 0) (nth (nth h si) 0)) r si)))]
                      (if (= smallest i)
                        h
                        (recur (-> h
                                   (assoc i (nth h smallest))
                                   (assoc smallest (nth h i)))
                               smallest))))))
              (heap-push [heap item]
                (heapify-up (conj heap item) (count heap)))
              (heap-pop [heap]
                (let [cnt (count heap)]
                  (cond
                    (zero? cnt) [nil []]
                    (= cnt 1) [(nth heap 0) []]
                    :else (let [root (nth heap 0)
                                last-item (nth heap (dec cnt))
                                new-heap (assoc (pop heap) 0 last-item)]
                            [root (heapify-down new-heap 0)]))))]
        (loop [heap (reduce
                      (fn [h i]
                        (heap-push h [(+ (nums1 i) (nums2 0)) i 0]))
                      []
                      (range (min n1 k)))
               remaining k
               result []]
          (if (or (zero? remaining) (empty? heap))
            result
            (let [[[ _ i j] heap'] (heap-pop heap)
                  result' (conj result [(nums1 i) (nums2 j)])
                  heap'' (if (< (inc j) n2)
                           (heap-push heap' [(+ (nums1 i) (nums2 (inc j))) i (inc j)])
                           heap')]
              (recur heap'' (dec remaining) result')))))))