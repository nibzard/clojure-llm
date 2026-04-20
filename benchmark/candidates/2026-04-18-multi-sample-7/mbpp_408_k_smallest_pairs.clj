(defn k_smallest_pairs
  "	Write a function to find k number of smallest pairs which consist of one element from the first vector and one element from the second vector."
  [nums1 nums2 k]
  (let [a (vec (or nums1 []))
        b (vec (or nums2 []))
        n1 (count a)
        n2 (count b)
        k  (max 0 (or k 0))]
    (if (or (zero? k) (zero? n1) (zero? n2))
      []
      (letfn [(heap-push [heap entry]
                (loop [h (conj heap entry)
                       i (dec (count (conj heap entry)))]
                  (if (zero? i)
                    h
                    (let [p (quot (dec i) 2)
                          ei (nth h i)
                          ep (nth h p)]
                      (if (< (first ei) (first ep))
                        (recur (-> h
                                   (assoc i ep)
                                   (assoc p ei))
                               p)
                        h)))))
              (heap-pop [heap]
                (let [cnt (count heap)]
                  (cond
                    (zero? cnt) [nil []]
                    (= cnt 1) [(first heap) []]
                    :else
                    (let [root (first heap)
                          last-elem (peek heap)
                          h (assoc (pop heap) 0 last-elem)
                          size (count h)]
                      (loop [h h
                             i 0]
                        (let [l (inc (* 2 i))
                              r (+ 2 (* 2 i))]
                          (if (>= l size)
                            [root h]
                            (let [smallest-child (if (and (< r size)
                                                          (< (first (nth h r))
                                                             (first (nth h l))))
                                                   r
                                                   l)
                                  ei (nth h i)
                                  ec (nth h smallest-child)]
                              (if (< (first ec) (first ei))
                                (recur (-> h
                                           (assoc i ec)
                                           (assoc smallest-child ei))
                                       smallest-child)
                                [root h]))))))))]
        (loop [i 0
               heap []]
          (if (< i (min k n1))
            (recur (inc i)
                   (heap-push heap [(+ (a i) (b 0)) i 0]))
            (loop [heap heap
                   remaining k
                   result []]
              (if (or (zero? remaining) (empty? heap))
                result
                (let [[[ _ i j] heap'] (heap-pop heap)
                      result' (conj result [(a i) (b j)])
                      heap'' (if (< (inc j) n2)
                               (heap-push heap' [(+ (a i) (b (inc j))) i (inc j)])
                               heap')]
                  (recur heap'' (dec remaining) result')))))))))