(defn sum_even_and_even_index
  "	Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (->> arr
       (keep-indexed (fn [idx x]
                       (when (and (even? idx)
                                  (number? x)
                                  (even? x))
                         x)))
       (reduce + 0)))