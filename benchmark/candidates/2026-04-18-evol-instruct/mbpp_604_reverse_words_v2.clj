(defn reverse-sections
  "Return a string with the order of whitespace-separated sections reversed.
  Preserve the original characters inside each section.

  Examples:
  (reverse-sections \"one two three\") => \"three two one\"
  (reverse-sections \"  a  b c \") => \" c b  a  \"
  (reverse-sections nil) => \"\""
  [s]
  (if (nil? s)
    ""
    (apply str (reverse (re-seq #"\s+|\S+" s)))))