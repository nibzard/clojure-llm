(defn replace_specialchar
  "Replace all spaces, commas, and dots in a string with a colon.

  Returns nil when given nil.

  Examples:
  (replace_specialchar \"a b,c.d\") ;=> \"a:b:c:d\"
  (replace_specialchar nil)        ;=> nil"
  [text]
  (when text
    (clojure.string/replace text #"[ ,\.]" ":")))