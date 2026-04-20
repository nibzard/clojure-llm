(defn max_of_nth
  "	Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (when (seq test_list)
    (->> test_list
         (keep #(nth % N nil))
         seq
         (apply max))))