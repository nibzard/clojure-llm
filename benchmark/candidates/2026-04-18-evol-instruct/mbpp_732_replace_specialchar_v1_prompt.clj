(defn redact-specialchar
  "Replace every occurrence of spaces, commas, or dots in the input string with a colon.

  Treat nil as an empty string.

  Examples:
  (redact-specialchar \"a b,c.d\") => \"a:b:c:d\"
  (redact-specialchar nil) => \"\""
  [text])