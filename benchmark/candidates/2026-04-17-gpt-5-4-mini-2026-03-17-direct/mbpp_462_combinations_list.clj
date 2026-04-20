(defn combinations_list
  "	Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (letfn [(combos [xs]
            (if (empty? xs)
              [[]]
              (let [rest-combos (combos (rest xs))
                    x (first xs)]
                (concat rest-combos
                        (map #(cons x %) rest-combos)))))]
    (combos list1)))