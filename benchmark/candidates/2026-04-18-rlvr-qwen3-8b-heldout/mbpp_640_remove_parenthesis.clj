(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (if (string? items)
    (clojure.string/replace items #"\([^)]*\)" "")
    items)