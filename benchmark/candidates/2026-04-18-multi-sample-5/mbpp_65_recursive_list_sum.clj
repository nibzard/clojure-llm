(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (letfn [(sum-nested [x]
            (cond
              (nil? x) 0
              (sequential? x) (reduce + 0 (map sum-nested x))
              :else x))]
    (sum-nested data_list)))