(defn combinations_list
  "	Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (if (empty? list1)
    '(())
    (let [rest-combs (combinations_list (rest list1))
          x (first list1)]
      (concat rest-combs
              (map #(cons x %) rest-combs)))))