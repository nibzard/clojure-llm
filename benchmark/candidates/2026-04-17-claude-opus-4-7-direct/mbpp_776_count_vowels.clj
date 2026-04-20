(defn count_vowels
  "Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        s (seq test_str)]
    (if (< (count s) 3)
      0
      (->> s
           (partition 3 1)
           (filter (fn [[_ b _]] (vowels b)))
           (filter (fn [[a _ c]] (and (vowels a) (vowels c))))
           count))))