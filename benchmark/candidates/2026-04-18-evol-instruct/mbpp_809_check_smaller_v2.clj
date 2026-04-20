(defn check_smaller_lazy
  "Return true if every element of the second sequence is smaller than the corresponding element of the first sequence.
  Works lazily on infinite sequences and returns false as soon as a violation is found.

  Examples:
  (check_smaller_lazy [3 5 8] [2 4 7]) => true
  (check_smaller_lazy [3 5 8] [2 6 7]) => false
  (check_smaller_lazy (repeat 10) (range)) => false"
  [xs ys]
  (every? true?
          (map < ys xs)))