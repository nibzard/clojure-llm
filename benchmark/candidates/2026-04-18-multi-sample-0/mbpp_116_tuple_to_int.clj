(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (if (seq nums)
    (reduce (fn [acc n]
              (+ (* acc (loop [x n
                               d 0]
                          (if (zero? x)
                            (max d 1)
                            (recur (quot x 10) (inc d)))))
                 n))
            0
            nums)
    0))