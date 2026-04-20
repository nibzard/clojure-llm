(defn sum_of_digits
  "Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (map (fn [n]
         (reduce + (map #(Character/digit % 10)
                        (filter #(Character/isDigit %) (str n)))))
       nums))