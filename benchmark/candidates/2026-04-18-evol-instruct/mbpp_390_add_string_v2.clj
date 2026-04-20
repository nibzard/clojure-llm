(defn filter-string
  "Return a lazy sequence of all strings in `coll` that match the regular expression `re`.

  Works with strings, vectors, and infinite sequences.

  Examples:
  (filter-string #\"^a\" [\"apple\" \"banana\" \"avocado\"]) ;=> (\"apple\" \"avocado\")
  (take 2 (filter-string #\"o\" (repeat \"foo\"))) ;=> (\"foo\" \"foo\")"
  [re coll]
  (filter #(re-find re %) coll))