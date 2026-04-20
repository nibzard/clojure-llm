(defn max_of_nth
  "	Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (when (and (seq test_list) (some #(> (count %) N) test_list))
    (apply max
           (keep #(nth % N nil) test_list))))