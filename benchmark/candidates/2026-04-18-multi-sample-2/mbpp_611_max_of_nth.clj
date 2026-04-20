(defn max_of_nth
  "	Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (when (seq test_list)
    (let [col (keep #(nth % N nil) test_list)]
      (when (seq col)
        (apply max col)))))