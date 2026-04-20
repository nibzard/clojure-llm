(defn heap_sort
  "	Write a function to sort the given list."
  [iterable]
  (let [v (vec iterable)
        n (count v)
        swap (fn [arr i j]
               (assoc arr i (arr j) j (arr i)))
        sift-down (fn [arr start end]
                    (loop [arr arr
                           root start]
                      (let [child (+ (* 2 root) 1)]
                        (if (> child end)
                          arr
                          (let [child' (if (and (< child end)
                                                (< (arr child) (arr (inc child))))
                                         (inc child)
                                         child)]
                            (if (< (arr root) (arr child'))
                              (recur (swap arr root child') child')
                              arr))))))]
    (if (zero? n)
      '()
      (let [heap (loop [arr v
                        start (quot (- n 2) 2)]
                   (if (neg? start)
                     arr
                     (recur (sift-down arr start (dec n)) (dec start))))
            sorted (loop [arr heap
                          end (dec n)]
                     (if (zero? end)
                       arr
                       (recur (sift-down (swap arr 0 end) 0 (dec end))
                              (dec end))))]
        (seq sorted)))))