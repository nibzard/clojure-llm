(defn get_closest_vowel
  [word]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        chars (vec word)
        n (count chars)]
    (loop [i (- n 2)]
      (cond
        (<= i 0) ""
        (and (vowels (chars i))
             (not (vowels (chars (dec i))))
             (not (vowels (chars (inc i)))))
        (str (chars i))
        :else
        (recur (dec i))))))