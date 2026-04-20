(defn max_of_nth
  "Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (if (empty? test_list)
    nil
    (apply max (map #(nth % N) test_list))))