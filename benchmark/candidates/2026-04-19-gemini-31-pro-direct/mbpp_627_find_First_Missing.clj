(defn find_First_Missing
  "Finds the smallest missing number from a sorted list of natural numbers."
  [array]
  (loop [i 0
         [x & xs] array]
    (if (nil? x)
      i
      (if (= i x)
        (recur (inc i) xs)
        i))))