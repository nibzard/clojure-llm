(defn count-distinct-pairs
  "Return the number of unordered pairs of distinct elements in a collection.

  Nil values are ignored. Works with any sequential collection, including
  infinite ones if the needed portion is realized.

  Examples:
  (count-distinct-pairs [1 1 2 3]) ;=> 5
  ;; pairs are: [1 2] [1 3] [1 2] [1 3] [2 3]

  (count-distinct-pairs [nil 4 4 5]) ;=> 2"
  [coll]
  (let [freqs (frequencies (remove nil? coll))
        total (reduce + (vals freqs))]
    (if (< total 2)
      0
      (/ (- (* total total) (reduce + (map #(* % %) (vals freqs)))) 2))))