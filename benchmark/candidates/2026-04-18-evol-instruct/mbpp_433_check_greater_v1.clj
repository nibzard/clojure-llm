(defn count-greater
  "Count how many elements in a (possibly nil) vector are greater than the given threshold.

  Examples:
  (count-greater [1 5 3 9] 4)   ;=> 2
  (count-greater [] 10)         ;=> 0
  (count-greater nil 2)         ;=> 0"
  [v threshold]
  (count (filter #(> % threshold) (or v []))))