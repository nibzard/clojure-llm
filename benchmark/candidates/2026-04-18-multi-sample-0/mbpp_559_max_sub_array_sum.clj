(defn max_sub_array_sum
  "	Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (empty? xs)
      0
      (reduce (fn [[max-so-far current] x]
                (let [current' (max x (+ current x))]
                  [(max max-so-far current') current']))
              [(first xs) (first xs)]
              (rest xs)))))