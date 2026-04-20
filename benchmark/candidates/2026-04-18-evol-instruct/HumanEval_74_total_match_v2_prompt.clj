(defn total_match_lazy
  "Return the lazy sequence whose strings have the smaller total character count.
  If both sequences have the same total, return the first sequence.

  The function must work with any seqable collection of strings, including lazy
  sequences and infinite sequences.

  Examples:
  >>> (total_match_lazy [\"hi\" \"admin\"] [\"hI\" \"Hi\"])
  (\"hI\" \"Hi\")
  >>> (total_match_lazy [\"a\" \"bb\"] [\"ccc\"])
  (\"a\" \"bb\")
  >>> (take 3 (total_match_lazy (repeat \"a\") [\"bbbb\"]))
  (\"a\" \"a\" \"a\")
  "
  [s1 s2])