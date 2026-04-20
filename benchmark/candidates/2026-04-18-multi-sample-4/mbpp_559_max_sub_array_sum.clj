(defn max_sub_array_sum
  "	Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (seq xs)
      (reduce (fn [[max-so-far curr-sum] x]
                (let [curr (max x (+ curr-sum x))
                      best (max max-so-far curr)]
                  [best curr]))
              [(first xs) (first xs)]
              (rest xs))
      0)))