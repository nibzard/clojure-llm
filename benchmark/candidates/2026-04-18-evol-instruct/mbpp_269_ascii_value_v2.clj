(defn ascii-sum
  "Return the sum of ASCII values for all characters in a string.

  Treat nil as an empty string.

  Examples:
  (ascii-sum \"A\")      ;;=> 65
  (ascii-sum \"abc\")    ;;=> 294
  (ascii-sum nil)        ;;=> 0"
  [s]
  (reduce + 0 (map int (or s ""))))