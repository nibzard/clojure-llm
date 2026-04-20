(defn sum_of_digits
  "	Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (mapv (fn [n]
          (->> (str (or n 0))
               (remove #(= % \-))
               (map #(- (int %) (int \0)))
               (reduce + 0)))
        (or nums [])))