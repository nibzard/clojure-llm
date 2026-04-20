(defn max_sub_array_sum
  "	Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (empty? xs)
      0
      (reduce (fn [[max-so-far curr-sum] x]
                (let [new-curr (max x (+ curr-sum x))
                      new-max  (max max-so-far new-curr)]
                  [new-max new-curr]))
              [(first xs) (first xs)]
              (rest xs)))))