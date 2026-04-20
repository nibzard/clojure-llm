(defn sum_even_and_even_index
  "	Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (reduce-kv (fn [sum idx n]
               (if (and (even? idx) (even? n))
                 (+ sum n)
                 sum))
             0
             (vec arr)))