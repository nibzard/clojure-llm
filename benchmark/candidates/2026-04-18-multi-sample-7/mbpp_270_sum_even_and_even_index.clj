(defn sum_even_and_even_index
  "	Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (->> arr
       (map-indexed vector)
       (filter (fn [[idx x]]
                 (and (even? idx)
                      (number? x)
                      (even? x))))
       (map second)
       (reduce + 0)))