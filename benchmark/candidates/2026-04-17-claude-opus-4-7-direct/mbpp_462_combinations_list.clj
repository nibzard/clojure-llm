(defn combinations_list
  "Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (if (empty? list1)
    '(())
    (let [rest-combos (combinations_list (rest list1))
          first-element (first list1)]
      (concat rest-combos
              (map #(conj % first-element) rest-combos)))))