(defn concatenate
  " Concatenate list of strings into a single string
  >>> (concatenate [])
  ""
  >>> (concatenate [\"a\" \"b\" \"c\"])
  \"abc\""
  [strings]
  (apply str (or strings [])))