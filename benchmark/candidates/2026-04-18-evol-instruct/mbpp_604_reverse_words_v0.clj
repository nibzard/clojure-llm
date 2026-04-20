(defn reverse_words
  "Return a sequence of words in reverse order.

  The input is a vector of words, not a string. Preserve the original word values.

  Examples:
  (reverse_words [\"hello\" \"world\"]) => [\"world\" \"hello\"]
  (reverse_words []) => []
  (reverse_words [\"a\" \"b\" \"c\"]) => [\"c\" \"b\" \"a\"]"
  [words]
  (vec (reverse words)))