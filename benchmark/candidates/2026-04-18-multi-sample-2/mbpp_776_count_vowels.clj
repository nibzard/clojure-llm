(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [s (or test_str "")
        vowels? #{\a \e \i \o \u \A \E \I \O \U}
        n (count s)]
    (if (< n 3)
      0
      (count
       (filter true?
               (map (fn [i]
                      (and (vowels? (nth s (dec i)))
                           (vowels? (nth s (inc i)))))
                    (range 1 (dec n))))))))