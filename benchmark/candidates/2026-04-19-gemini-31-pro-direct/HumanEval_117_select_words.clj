(defn select_words [s n]
  (let [words (re-seq #"[a-zA-Z]+" s)
        vowels #{\a \e \i \o \u \A \E \I \O \U}]
    (vec (filter #(= n (count (remove vowels %))) words))))