(defn decimal_to_binary
  "Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (when n
    (.toString (biginteger n) 2)))