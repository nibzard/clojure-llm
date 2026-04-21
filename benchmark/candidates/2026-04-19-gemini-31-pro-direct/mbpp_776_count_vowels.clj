(defn count_vowels
  "Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}]
    (->> test_str
         (partition 3 1)
         (filter (fn [[l _ r]] (and (vowels l) (vowels r))))
         count)))