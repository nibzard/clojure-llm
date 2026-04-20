(defn nth_nums
  "	Write a function to compute the n-th power of each number in a list."
  [nums n]
  (map #(Math/pow % n) nums))