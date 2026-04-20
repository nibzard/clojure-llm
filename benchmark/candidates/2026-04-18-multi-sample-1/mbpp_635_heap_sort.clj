(defn heap_sort
  "	Write a function to sort the given list."
  [iterable]
  (let [v (vec (or iterable []))
        n (count v)
        sift-down
        (fn [arr start end]
          (loop [arr arr
                 root start]
            (let [child (+ (* 2 root) 1)]
              (if (> child end)
                arr
                (let [right (inc child)
                      swap-idx (cond
                                 (and (<= right end)
                                      (> (arr right) (arr child)))
                                 right
                                 :else child)]
                  (if (< (arr root) (arr swap-idx))
                    (recur (-> arr
                               (assoc root (arr swap-idx))
                               (assoc swap-idx (arr root)))
                           swap-idx)
                    arr))))))]
    (if (<= n 1)
      (seq v)
      (let [heapified
            (loop [arr v
                   start (quot (- n 2) 2)]
              (if (neg? start)
                arr
                (recur (sift-down arr start (dec n))
                       (dec start))))]
        (loop [arr heapified
               end (dec n)]
          (if (zero? end)
            (seq arr)
            (recur (sift-down (-> arr
                                  (assoc 0 (arr end))
                                  (assoc end (arr 0)))
                              0
                              (dec end))
                   (dec end))))))))