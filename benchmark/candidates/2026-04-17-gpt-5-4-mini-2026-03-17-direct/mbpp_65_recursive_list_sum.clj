(defn recursive_list_sum
  "Write a function to flatten a list and sum all of its elements."
  [data_list]
  (letfn [(sum-items [x]
            (cond
              (nil? x) 0
              (sequential? x) (reduce + 0 (map sum-items x))
              (number? x) x
              :else 0))]
    (sum-items data_list)))