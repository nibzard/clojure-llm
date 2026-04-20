(defn get_median
  "Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (if (<= n 0)
    -1
    (let [merged (sort (concat arr1 arr2))
          mid1 (nth merged (dec n))
          mid2 (nth merged n)]
      (/ (+ mid1 mid2) 2))))