(defn max_of_nth
  "	Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (when (and (seq test_list)
             (integer? N)
             (not (neg? N)))
    (->> test_list
         (keep #(nth % N nil))
         seq
         (apply max))))