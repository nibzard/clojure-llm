(defn max_sub_array_sum
  "Write a function to find the sum of the largest contiguous sublist in the given list."
  [a size]
  (let [xs (take size (or a []))]
    (if (seq xs)
      (reduce (fn [[max-so-far max-ending-here] x]
                (let [meh (max x (+ max-ending-here x))
                      msf (max max-so-far meh)]
                  [msf meh]))
              [(first xs) (first xs)]
              (rest xs))
      0)))