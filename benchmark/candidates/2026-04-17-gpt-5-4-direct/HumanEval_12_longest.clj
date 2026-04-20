(defn longest
  " Out of list of strings, return the longest one. Return the first one in case of multiple
  strings of the same length. Return nil in case the input list is empty.
  >>> (longest [])
  nil
  >>> (longest [\"a\" \"b\" \"c\"])
  \"a\"
  >>> (longest [\"a\" \"bb\" \"ccc\"])
  \"ccc\""
  [strings]
  (reduce (fn [longest-so-far s]
            (if (> (count s) (count longest-so-far))
              s
              longest-so-far))
          strings))