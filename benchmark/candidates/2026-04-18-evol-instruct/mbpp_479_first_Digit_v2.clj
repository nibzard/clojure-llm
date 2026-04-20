(defn first-digit-in-string
  "Return the first numeric digit character in s, or nil if none exists.

  Examples:
  (first-digit-in-string \"ab12cd\") => \\1
  (first-digit-in-string \"no digits\") => nil
  (first-digit-in-string \"x9y\") => \\9"
  [s]
  (first (filter #(Character/isDigit ^char %) s)))