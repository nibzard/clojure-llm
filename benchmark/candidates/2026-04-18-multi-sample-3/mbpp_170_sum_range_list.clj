(defn sum_range_list
  "	Write a function to find the sum of numbers in a list within a range specified by two indices."
  [list1 m n]
  (let [start (max 0 (min m n))
        end   (max m n)]
    (->> list1
         (drop start)
         (take (inc (- end start)))
         (reduce + 0))))