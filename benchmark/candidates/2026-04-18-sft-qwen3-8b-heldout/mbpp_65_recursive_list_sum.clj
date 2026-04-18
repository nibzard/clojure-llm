(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (letfn [(sum-elements [x]
            (cond
              (nil? x) 0
              (number? x) x
              (sequential? x) (reduce + 0 (map sum-elements x))
              :else 0))]
    (sum-elements data_list)))