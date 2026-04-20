(defn longest-pal-prefix
  "Return the length of the longest palindromic prefix of a collection.

  Accepts any sequential collection, including strings, vectors, and lists.
  Returns 0 for nil or an empty collection.

  Examples:
  (longest-pal-prefix \"abac\")   ;=> 3   because \"aba\" is a palindromic prefix
  (longest-pal-prefix [1 2 1 3])  ;=> 3   because [1 2 1] is a palindromic prefix
  (longest-pal-prefix nil)       ;=> 0"
  [coll]
  (let [xs (seq coll)]
    (if (nil? xs)
      0
      (loop [prefix [] remaining xs]
        (if (empty? remaining)
          (count prefix)
          (let [candidate (conj prefix (first remaining))]
            (if (= candidate (reverse candidate))
              (recur candidate (rest remaining))
              (count prefix))))))))