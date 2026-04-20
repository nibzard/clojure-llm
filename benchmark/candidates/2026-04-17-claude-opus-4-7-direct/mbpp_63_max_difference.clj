(defn max_difference
  "Write a function to find the maximum difference between available pairs in the given vector list."
  [test_list]
  (if (or (nil? test_list) (< (count test_list) 2))
    0
    (let [sorted-list (sort test_list)
          min-val (first sorted-list)
          max-val (last sorted-list)]
      (- max-val min-val))))