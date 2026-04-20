(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [merged (sort (concat arr1 arr2))
        m (count merged)]
    (if (zero? m)
      nil
      (/ (+ (nth merged (dec (quot m 2)))
            (nth merged (quot m 2)))
         2.0))))