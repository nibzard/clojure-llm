(defn vowels_count
  "Write a function vowels_count which takes a string representing
  a word as input and returns the number of vowels in the string.
  Vowels in this case are 'a', 'e', 'i', 'o', 'u'. Here, 'y' is also a
  vowel, but only when it is at the end of the given word."
  [s]
  (if (empty? s)
    0
    (let [lower-s (clojure.string/lower-case s)
          standard-vowels (count (filter #{\a \e \i \o \u} lower-s))]
      (if (= \y (last lower-s))
        (inc standard-vowels)
        standard-vowels))))