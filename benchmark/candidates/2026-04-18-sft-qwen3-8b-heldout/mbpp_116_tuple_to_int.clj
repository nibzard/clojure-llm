(defn tuple_to_int
  "	Write a function to convert a given vector of positive integers into a single integer."
  [nums]
  (reduce (fn [acc x] (+ (* acc 10) x)) 0 nums))