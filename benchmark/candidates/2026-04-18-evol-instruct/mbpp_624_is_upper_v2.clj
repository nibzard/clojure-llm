(defn is_upper
  "Return a new string with all alphabetic characters converted to uppercase.

  Examples:
  (is_upper \"clj\") => \"CLJ\"
  (is_upper nil)    => nil"
  [string]
  (when string
    (.toUpperCase string)))