(defn find_adverb_position
  [text]
  (when text
    (let [matcher (re-matcher #"\w+ly" text)]
      (when (.find matcher)
        [(.start matcher) (.end matcher) (.group matcher)]))))