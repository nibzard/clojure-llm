(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (if (seq nums)
    (reduce (fn [acc n]
              (+ (* acc 10)
                 (loop [x n
                        digits 1]
                   (if (< x 10)
                     n
                     (recur (quot x 10) (inc digits))))))
            0
            nums)
    0))