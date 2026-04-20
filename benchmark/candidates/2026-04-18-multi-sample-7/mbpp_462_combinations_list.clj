(defn combinations_list
  "	Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (let [items (seq list1)]
    (if (nil? items)
      '(())
      (let [rest-combos (combinations_list (rest items))]
        (concat rest-combos
                (map #(cons (first items) %) rest-combos))))))