(defn combinations_colors
  "Write a function that takes in a list and length n, and generates all combinations (with repetition) of the elements of the list and returns a list with a list for each combination."
  [l n]
  (letfn [(combos [items k]
            (cond
              (zero? k) (list '())
              (empty? items) '()
              :else (lazy-seq
                     (concat
                      (map #(cons (first items) %) (combos items (dec k)))
                      (combos (rest items) k)))))]
    (combos l n)))