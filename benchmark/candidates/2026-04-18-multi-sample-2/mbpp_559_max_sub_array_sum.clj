(defn max_sub_array_sum
  "	Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (seq xs)
      (reduce (fn [[max-so-far current] x]
                (let [current' (max x (+ current x))
                      max' (max max-so-far current')]
                  [max' current']))
              [(first xs) (first xs)]
              (rest xs))
      0)))