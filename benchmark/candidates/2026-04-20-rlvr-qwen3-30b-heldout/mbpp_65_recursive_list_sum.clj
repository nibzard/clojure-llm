(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (letfn [(flatten-and-sum [x]
            (cond
              (nil? x) 0
              (number? x) x
              (sequential? x) (reduce + 0 (map flatten-and-sum x))
              :else 0))]
    (flatten-and-sum data_list)))