(defn sum_range_list
  "	Write a function to find the sum of numbers in a list within a range specified by two indices."
  [list1 m n]
  (let [start (min m n)
        end (max m n)]
    (if (or (nil? list1) (empty? list1))
      0
      (->> list1
           (drop start)
           (take (inc (- end start)))
           (reduce + 0)))))