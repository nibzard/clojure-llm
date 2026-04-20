(defn max_of_nth
  "	Write a function which given a matrix represented as a list of lists returns the max of the n'th column."
  [test_list N]
  (when (and (sequential? test_list)
             (number? N)
             (not (neg? N)))
    (let [col (keep #(nth % N nil) test_list)]
      (when (seq col)
        (apply max col)))))