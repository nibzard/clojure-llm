(defn rotation-index
  "Return the index of the smallest element in a rotated sorted vector.

  The input vector may contain duplicate values. If the vector is empty, return nil.

  Examples:
  (rotation-index [15 18 2 3 6 12]) ;=> 2
  (rotation-index [7 9 11 12 5])    ;=> 4
  (rotation-index [1 2 3 4])        ;=> 0
  (rotation-index [])               ;=> nil"
  [v]
  (when (seq v)
    (let [m (apply min v)]
      (first (keep-indexed #(when (= %2 m) %1) v)))))