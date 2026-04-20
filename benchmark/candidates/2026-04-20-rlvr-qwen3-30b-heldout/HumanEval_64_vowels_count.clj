(defn vowels_count
  "Write a function vowels_count which takes a string representing
  a word as input and returns the number of vowels in the string.
  Vowels in this case are 'a', 'e', 'i', 'o', 'u'. Here, 'y' is also a
  vowel, but only when it is at the end of the given word.
  Example:
  >>> (vowels_count \"abcde\")
  2
  >>> (vowels_count \"ACEDY\")
  3"
  [s]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        last-char (when (seq s) (last s))]
    (+ (count (filter vowels s))
       (if (and last-char (= last-char \y)) 1 0))))