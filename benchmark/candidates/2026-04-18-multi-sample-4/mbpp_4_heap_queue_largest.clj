(defn heap_queue_largest
  "	Write a function to find the n largest integers from a given list of numbers, returned in descending order."
  [nums n]
  (let [n (max 0 (or n 0))]
    (->> nums
         (filter number?)
         sort
         reverse
         (take n))))