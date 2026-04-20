(defn maximum
  "Given a vector arr of integers and a positive integer k, return a sorted list 
  of length k with the maximum k numbers in arr."
  [arr k]
  (if (zero? k)
    []
    (->> arr
         (sort)
         (take-last k))))