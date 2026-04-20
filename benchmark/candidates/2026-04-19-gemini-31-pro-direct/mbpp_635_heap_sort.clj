(defn heap_sort
  " Write a function to sort the given list."
  [iterable]
  (if (empty? iterable)
    []
    (let [pq (java.util.PriorityQueue. ^java.util.Collection (seq iterable))]
      (loop [acc []]
        (if (.isEmpty pq)
          acc
          (recur (conj acc (.poll pq))))))))