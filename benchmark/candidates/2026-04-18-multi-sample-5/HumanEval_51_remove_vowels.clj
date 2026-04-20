(defn remove_vowels
  "remove_vowels is a function that takes string and returns string without vowels.
  >>> (remove_vowels "")
  ""
  >>> (remove_vowels "abcdef")
  "bcdf"
  >>> (remove_vowels "aaaaa")
  ""
  >>> (remove_vowels "aaBAA")
  "B"
  >>> (remove_vowels "zbcd")
  \"zbcd\""
  [text]
  (apply str (remove #{\a \e \i \o \u \A \E \I \O \U} (or text ""))))