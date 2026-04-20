(defn join-keywords
  "Return a single string by concatenating a sequence of keywords with a delimiter.

  The function should:
  - accept any sequence of keywords
  - ignore nil values
  - return an empty string for an empty input
  - work with lazy sequences

  Examples:
  (join-keywords [:a :b :c] \"/\") ;=> \"a/b/c\"
  (join-keywords [nil :x nil :y] \",\") ;=> \"x,y\"
  (join-keywords () \"-\") ;=> \"\""
  [keywords delim]
  (clojure.string/join delim (keep #(when % (name %)) keywords)))