(defn max_sub_array_sum
  "	Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size a)]
    (if (seq xs)
      (first
       (reduce (fn [[best curr] x]
                 (let [curr' (max x (+ curr x))
                       best' (max best curr')]
                   [best' curr']))
               [(first xs) (first xs)]
               (rest xs)))
      0)))