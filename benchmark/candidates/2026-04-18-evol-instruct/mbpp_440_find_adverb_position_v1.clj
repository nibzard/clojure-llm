(defn find_palindrome_position
  "Return the first palindromic word in a sentence and its zero-based position.

  A palindromic word reads the same forwards and backwards, ignoring case.
  If no palindromic word exists, return nil.

  Examples:
  (find_palindrome_position \"level up the radar\") => [\"level\" 0]
  (find_palindrome_position \"A man a plan\") => nil"
  [text]
  (first
    (keep-indexed
      (fn [i w]
        (let [s (clojure.string/lower-case w)]
          (when (= s (clojure.string/reverse s))
            [w i])))
      (clojure.string/split (or text "") #"\s+"))))