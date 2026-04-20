(defn select_words
  [s n]
  (let [consonants (set "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ")]
    (if (clojure.string/blank? s)
      []
      (->> (clojure.string/split s #"\s+")
           (filter (fn [word]
                     (= n (count (filter consonants word)))))
           (into [])))))