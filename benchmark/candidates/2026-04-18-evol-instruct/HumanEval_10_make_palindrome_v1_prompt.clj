(defn longest-palindromic-prefix
  "Return the shortest palindrome that ends with the supplied string.

  The function should append the fewest possible characters to the *front*
  of the string so that the result is a palindrome.

  Examples:
  (longest-palindromic-prefix \"\")       ;=> \"\"
  (longest-palindromic-prefix \"cat\")    ;=> \"tacat\"
  (longest-palindromic-prefix \"cata\")   ;=> \"atacata\"

  Notes:
  - Treat nil as the empty string.
  - The input may be a string or any seqable of characters.
  - Return a string."
  [s])