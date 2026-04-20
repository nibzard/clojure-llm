(defn count-palindromic-strings
  "Given a collection of strings, return a vector with the number of even-length
  and odd-length palindromic strings, respectively.

  A string counts as palindromic if it reads the same forwards and backwards.
  Ignore nil values and treat an empty string as a palindrome of even length.

  Examples:
  >>> (count-palindromic-strings [\"aa\" \"abc\" nil \"racecar\" \"\" \"noon\"])
  [3 2]
  Explanation:
  Palindromic strings are \"aa\", \"racecar\", \"\", and \"noon\".
  Even-length palindromes: \"aa\", \"\", \"noon\" -> 3
  Odd-length palindromes: \"racecar\" -> 1
  Wait: \"noon\" is even, so the result is [3 1].

  >>> (count-palindromic-strings [\"a\" \"bb\" \"ccc\" \"dd\" \"x\"])
  [2 3]

  Constraints:
  - Handle nil elements by ignoring them.
  - Use the strings exactly as provided; no normalization is needed."
  [strings])