(defn sum_range_list
  "	Write a function to find the sum of numbers in a list within a range specified by two indices."
  [list1 m n]
  (let [xs (or list1 [])
        start (max 0 (min m n))
        end (max m n)]
    (reduce + 0 (take (inc (- end start)) (drop start xs)))))