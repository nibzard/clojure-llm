(defn reverse_delete
  "Task
  We are given two strings s and c, you have to deleted all the characters in s that are equal to any character in c
  then check if the result string is palindrome.
  A string is called palindrome if it reads the same backward as forward.
  You should return a vector containing the result string and true/false for the check.
  Example
  >>> (reverse_delete "abcde" "ae")
  ["bcd" false]
  >>> (reverse_delete "abcdef" "b")
  ["acdef" false]
  >>> (reverse_delete "abcdedcba" "ab")
  ["cdedc" true]"
  [s c]
  
  nil)
