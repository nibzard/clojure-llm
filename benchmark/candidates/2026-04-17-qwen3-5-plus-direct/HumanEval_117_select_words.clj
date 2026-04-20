(defn select_words
  [s n]
  (if (empty? s)
    []
    (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
          words (clojure.string/split s #"\s+")
          count-consonants (fn [word]
                             (count (filter #(and (Character/isLetter %)
                                                  (not (vowels %)))
                                            word)))]
      (vec (filter #(= n (count-consonants %)) words)))))