(defn sum_of_digits
  "	Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (map (fn [n]
         (reduce + 0 (map #(Character/digit ^char % 10) (str (Math/abs (long n))))))
       nums))