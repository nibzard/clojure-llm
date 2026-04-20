(defn max_sub_array_sum
  "Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (if (empty? a)
    0
    (loop [xs (rest a)
           curr-max (first a)
           max-so-far (first a)]
      (if-let [x (first xs)]
        (let [new-curr (max x (+ curr-max x))]
          (recur (rest xs) new-curr (max max-so-far new-curr)))
        max-so-far))))