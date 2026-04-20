(defn combinations_colors
  "Write a function that takes in a list and length n, and generates all combinations (with repetition) of the elements of the list and returns a list with a list for each combination."
  [l n]
  (if (zero? n)
    '(())
    (for [element l
          combination (combinations_colors l (dec n))]
      (cons element combination))))