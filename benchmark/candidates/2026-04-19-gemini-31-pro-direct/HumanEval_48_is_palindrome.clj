(defn is_palindrome
  "Checks if given string is a palindrome"
  [text]
  (= text (apply str (reverse text))))