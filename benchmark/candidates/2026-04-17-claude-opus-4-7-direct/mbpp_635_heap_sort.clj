(defn heap_sort
  "Write a function to sort the given list."
  [iterable]
  (let [coll (if (vector? iterable) iterable (vec iterable))
        n (count coll)]
    (if (<= n 1)
      coll
      (letfn [(heapify [coll i size]
                (loop [i i
                       coll coll]
                  (let [left (inc (* 2 i))
                        right (inc (* 2 (inc i)))
                        largest (cond
                                  (and (< left size) (> (coll left) (coll i))) left
                                  :else i)
                        largest (if (and (< right size) (> (coll right) (coll largest)))
                                  right
                                  largest)]
                    (if (not= largest i)
                      (recur largest (assoc coll i (coll largest) largest (coll i)))
                      coll))))
              (build-heap [coll]
                (reduce #(heapify %1 %2 n) coll (range (quot n 2) -1 -1)))
              (sort-heap [coll]
                (loop [i (dec n)
                       coll (build-heap coll)]
                  (if (zero? i)
                    coll
                    (recur (dec i) (heapify (assoc coll 0 (coll i) i (coll 0)) 0 i)))))]
        (sort-heap coll)))))