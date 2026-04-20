(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (reduce (fn [acc n]
            (+ (* acc 10) n))
          0
          nums))