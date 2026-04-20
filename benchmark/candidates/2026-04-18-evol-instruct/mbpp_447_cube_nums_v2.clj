(defn cube-transduce
  "Return a lazy sequence of cubes for the numbers in `nums`, using transduction-friendly semantics.

  Accepts any sequential collection, ignores nil values, and works with infinite sequences.

  Examples:
  (cube-transduce [1 2 3])
  => (1 8 27)

  (cube-transduce [nil -2 0 4])
  => (-8 0 64)

  (take 4 (cube-transduce (range)))
  => (0 1 8 27)"
  [nums]
  (sequence
    (comp
      (remove nil?)
      (map #(* % % %)))
    nums))