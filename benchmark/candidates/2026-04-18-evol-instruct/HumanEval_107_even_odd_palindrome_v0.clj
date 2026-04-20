(defn count-palindromic-strings
  "Given a collection of strings, return a vector with the number of even-length
  and odd-length palindromic strings, respectively.

  A string counts as palindromic if it reads the same forwards and backwards.
  Ignore nil values and treat an empty string as a palindrome of even length.

  Examples:
  >>> (count-palindromic-strings [\"aa\" \"abc\" nil \"racecar\" \"\" \"noon\"])
  [3 1]

  >>> (count-palindromic-strings [\"a\" \"bb\" \"ccc\" \"dd\" \"x\"])
  [2 3]

  Constraints:
  - Handle nil elements by ignoring them.
  - Use the strings exactly as provided; no normalization is needed."
  [strings]
  (reduce (fn [[even odd] s]
            (if (and s (= (seq s) (reverse s)))
              (if (even? (count s))
                [(inc even) odd]
                [even (inc odd)])
              [even odd]))
          [0 0]
          strings))