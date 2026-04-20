(defn select_words
  "Given a collection of words `words` and a natural number `n`, return a vector
  of the words whose total number of vowels is exactly `n`, preserving the
  original order.

  A word may be a string, a keyword, or a symbol. Count vowels in the printed
  name of each word, case-insensitively. Treat `a`, `e`, `i`, `o`, `u` as vowels.

  If `words` is nil or empty, return an empty vector.

  Examples:
  >>> (select_words [\"Mary\" \"had\" \"a\" \"little\" \"lamb\"] 2)
  [\"had\" \"little\"]
  >>> (select_words [\"Uncle\" :sam 'sky] 2)
  [\"Uncle\"]
  >>> (select_words [] 1)
  []"
  [words n]
  (let [vowels #{\a \e \i \o \u}
        vowel-count (fn [w]
                      (count (filter vowels
                                     (map #(Character/toLowerCase ^char %)
                                          (name w)))))]
    (->> words
         (filter #(= n (vowel-count %)))
         vec)))