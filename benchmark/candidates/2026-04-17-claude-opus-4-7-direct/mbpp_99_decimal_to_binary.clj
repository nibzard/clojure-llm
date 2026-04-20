(defn decimal_to_binary
  "Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (if (zero? n)
    "0"
    (Integer/toString n 2)))