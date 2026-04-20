(defn occurrence-indices
  "Return a lazy sequence of all starting indices where `pattern` occurs in `coll`.
  Works with vectors, lists, and strings. Return nil if there is no match.

  Examples:
  (occurrence-indices [1 2 3 2 3 4] [2 3]) ;=> (1 3)
  (occurrence-indices \"abracadabra\" \"abra\") ;=> (0 7)
  (occurrence-indices [1 2 3] [4]) ;=> nil"
  [coll pattern]
  (let [c (seq coll)
        p (seq pattern)
        pc (count pattern)]
    (when (and c p (pos? pc))
      (let [matches
            (keep-indexed
             (fn [i _]
               (when (= p (take pc (drop i c)))
                 i))
             c)]
        (seq matches)))))