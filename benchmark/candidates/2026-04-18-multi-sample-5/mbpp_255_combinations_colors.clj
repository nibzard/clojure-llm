(defn combinations_colors
  "	Write a function that takes in a list and length n, and generates all combinations (with repetition) of the elements of the list and returns a list with a list for each combination."
  [l n]
  (let [items (vec l)]
    (cond
      (nil? n) nil
      (neg? n) '()
      (zero? n) (list '())
      (empty? items) '()
      :else
      (letfn [(combos [start k]
                (if (zero? k)
                  (list '())
                  (apply concat
                         (for [i (range start (count items))]
                           (map #(cons (items i) %)
                                (combos i (dec k)))))))]
        (combos 0 n))))