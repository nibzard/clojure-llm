(defn sort-by-length-of-words
  "Return a new vector of vectors where each inner vector of strings is sorted by word length, shortest to longest.
  Preserve the outer order and the relative order of words with equal length.

  Examples:
  (sort-by-length-of-words [[\"pear\" \"fig\" \"apple\"] [\"a\" \"bbb\" \"cc\"]])
  => [[\"fig\" \"pear\" \"apple\"] [\"a\" \"cc\" \"bbb\"]]

  Handles nil inner collections as empty collections:
  (sort-by-length-of-words [nil [\"z\" \"yy\"]])
  => [[] [\"z\" \"yy\"]]"
  [colls]
  (mapv #(->> (or % [])
              (sort-by count)
              vec)
        colls))