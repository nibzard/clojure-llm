(defn sum_of_digits
  "	Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (mapv (fn [n]
          (->> (str (Math/abs (long n)))
               (map #(- (int %) (int \0)))
               (reduce + 0)))
        nums))