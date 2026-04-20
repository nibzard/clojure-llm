(defn sum_even_and_even_index
  "	Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (->> arr
       (map-indexed vector)
       (reduce (fn [sum [idx x]]
                 (if (and (even? idx) (number? x) (even? x))
                   (+ sum x)
                   sum))
               0)))