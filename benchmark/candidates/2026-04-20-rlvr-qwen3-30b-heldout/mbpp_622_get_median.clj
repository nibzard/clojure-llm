(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [merged (sort (concat (or arr1 []) (or arr2 [])))
        total  (count merged)
        mid    (quot total 2)]
    (if (odd? total)
      (nth merged mid)
      (/ (+ (nth merged (dec mid)) (nth merged mid)) 2.0))))