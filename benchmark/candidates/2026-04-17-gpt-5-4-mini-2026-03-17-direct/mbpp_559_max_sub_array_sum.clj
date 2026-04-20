(defn max_sub_array_sum
  "Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size a)]
    (if (empty? xs)
      0
      (second
       (reduce (fn [[current-best max-so-far] x]
                 (let [current-best (max x (+ current-best x))
                       max-so-far (max max-so-far current-best)]
                   [current-best max-so-far]))
               [(first xs) (first xs)]
               (rest xs))))))