(defn heap_queue_largest
  "	Write a function to find the n largest integers from a given list of numbers, returned in descending order."
  [nums n]
  (let [n (if (and (number? n) (pos? n)) (long n) 0)]
    (if (or (zero? n) (nil? nums))
      []
      (->> nums
           (sort >)
           (take n)
           vec))))