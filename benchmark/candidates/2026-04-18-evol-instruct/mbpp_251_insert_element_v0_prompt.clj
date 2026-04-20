(defn interleave-element
  "Return a lazy sequence with `element` inserted before each item of `coll`.

  Works with sequences of any type, including infinite sequences.

  Examples:
  (take 6 (interleave-element [1 2 3] :x))
  ;; => (:x 1 :x 2 :x 3)

  (take 5 (interleave-element (range) 0))
  ;; => (0 0 0 1 0)"
  [coll element])