(defn recursive_list_sum
  "	Write a function to flatten a list and sum all of its elements."
  [data_list]
  (letfn [(step [xs]
            (cond
              (nil? xs) 0
              (sequential? xs) (reduce + (map step xs))
              :else xs))]
    (step data_list)))