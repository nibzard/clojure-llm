(defn count-distinct-pairs
  "Return the number of unordered pairs of unequal elements in a collection.

Examples:
(count-distinct-pairs [1 1 2 2]) ;=> 4
(count-distinct-pairs [5])       ;=> 0
(count-distinct-pairs nil)       ;=> 0

Works with any sequential collection and should not realize infinite sequences unnecessarily."
  [xs]
  (let [freqs (vals (frequencies (or xs [])))
        total-pairs (reduce + 0 (for [a freqs
                                      b freqs
                                      :when (< a b)]
                                  (* a b)))]
    total-pairs))