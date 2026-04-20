(defn heap_queue_largest
  "Write a function to find the n largest integers from a given list of numbers, returned in descending order."
  [nums n]
  (let [cnt (count nums)]
    (cond
      (or (nil? nums) (zero? cnt) (<= n 0)) '()
      (>= n cnt) (sort > nums)
      :else (sort > (take n (sort > nums))))))