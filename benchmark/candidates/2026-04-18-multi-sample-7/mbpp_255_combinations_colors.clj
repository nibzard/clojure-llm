(defn combinations_colors
  "	Write a function that takes in a list and length n, and generates all combinations (with repetition) of the elements of the list and returns a list with a list for each combination."
  [l n]
  (let [items (seq l)]
    (cond
      (neg? n) '()
      (zero? n) (list '())
      (nil? items) '()
      :else
      (letfn [(combos [xs k]
                (cond
                  (zero? k) (list '())
                  (nil? xs) '()
                  :else
                  (lazy-seq
                    (concat
                      (map #(cons (first xs) %)
                           (combos xs (dec k)))
                      (combos (next xs) k)))))]
        (combos items n))))