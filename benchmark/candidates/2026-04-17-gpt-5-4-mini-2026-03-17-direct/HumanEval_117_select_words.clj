(defn select_words
  [s n]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}]
    (if (empty? s)
      []
      (->> (clojure.string/split s #"\s+")
           (filter (fn [word]
                     (= n (count (remove vowels word)))))
           vec))))