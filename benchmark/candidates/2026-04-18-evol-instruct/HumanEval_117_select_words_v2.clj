(defn select_words
  "Given a string s, return a vector of all words from s that contain exactly
  n consonants. Words are separated by whitespace and may include punctuation.
  Consonants are the English letters other than vowels (a, e, i, o, u),
  case-insensitive. Non-letter characters inside a word should be ignored when
  counting consonants.

  If s is nil or blank, return an empty vector.
  If n is negative, return an empty vector.

  Examples:
  >>> (select_words \"Mary had a little lamb\" 4)
  [\"little\"]
  >>> (select_words \"Hello, world!\" 4)
  [\"world!\"]
  >>> (select_words nil 2)
  []
  >>> (select_words \"a-b c_d\" 1)
  [\"a-b\" \"c_d\"]"
  [s n]
  (if (or (nil? s) (neg? n) (clojure.string/blank? s))
    []
    (let [vowels #{\a \e \i \o \u}
          consonant-count (fn [w]
                            (->> w
                                 (filter #(Character/isLetter ^char %))
                                 (filter #(and (Character/isLetter ^char %)
                                               (not (contains? vowels
                                                               (Character/toLowerCase ^char %)))))
                                 count))]
      (->> (clojure.string/split s #"\s+")
           (filter #(= n (consonant-count %)))
           vec))))