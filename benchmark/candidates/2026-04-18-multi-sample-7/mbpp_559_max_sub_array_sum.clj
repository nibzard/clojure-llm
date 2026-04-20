(defn max_sub_array_sum
  "Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (seq xs)
      (first
       (reduce (fn [[max-sum curr-sum] x]
                 (let [next-sum (max x (+ curr-sum x))]
                   [(max max-sum next-sum) next-sum]))
               [(first xs) (first xs)]
               (rest xs)))
      0)))