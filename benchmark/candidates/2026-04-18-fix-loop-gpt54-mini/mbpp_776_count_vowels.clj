(defn count_vowels
  "	Write a function to count those characters which have vowels as their neighbors in the given string."
  [test_str]
  (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
        s (vec test_str)
        n (count s)]
    (count
     (for [i (range 1 (dec n))
           :when (and (vowels (nth s (dec i)))
                      (vowels (nth s (inc i))))]
       (nth s i)))))