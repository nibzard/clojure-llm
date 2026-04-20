(defn max_sub_array_sum
  "Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (if (empty? a)
    0
    (loop [xs (rest a)
           current-max (first a)
           global-max (first a)]
      (if (empty? xs)
        global-max
        (let [next-x (first xs)
              new-current (max next-x (+ current-max next-x))
              new-global (max global-max new-current)]
          (recur (rest xs) new-current new-global))))))