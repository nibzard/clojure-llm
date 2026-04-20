(defn sum_even_and_even_index
  "Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (->> arr
       (keep-indexed (fn [idx val]
                       (when (and (even? idx) (even? val))
                         val)))
       (reduce + 0)))