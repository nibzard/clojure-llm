(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (if (seq nums)
    (reduce (fn [acc n]
              (+ (* acc (long (Math/pow 10 (count (str n))))) n))
            0
            nums)
    0))