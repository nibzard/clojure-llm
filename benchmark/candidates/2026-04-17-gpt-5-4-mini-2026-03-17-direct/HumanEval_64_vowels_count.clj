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
  (let [s (or s "")
        lower (clojure.string/lower-case s)
        last-ch (when (seq lower) (last lower))
        vowels #{\a \e \i \o \u}]
    (count
     (filter
      (fn [ch]
        (or (contains? vowels ch)
            (and (= ch \y) (= ch last-ch))))
      lower))))