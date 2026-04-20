(defn is_palindrome
  "Checks if given string is a palindrome
  >>> (is_palindrome \"\")
  true
  >>> (is_palindrome \"aba\")
  true
  >>> (is_palindrome \"aaaaa\")
  true
  >>> (is_palindrome \"zbcd\")
  false"
  [text]
  (let [s (or text "")]
    (= (seq s) (reverse s))))