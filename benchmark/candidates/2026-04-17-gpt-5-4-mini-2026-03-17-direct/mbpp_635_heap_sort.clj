(defn heap_sort
  "	Write a function to sort the given list."
  [iterable]
  (let [heap (java.util.PriorityQueue.)]
    (doseq [x iterable]
      (.add heap x))
    (loop [result []]
      (if (empty? heap)
        result
        (recur (conj result (.poll heap)))))))