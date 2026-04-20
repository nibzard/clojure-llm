(defn reverse_vowels
  "	Write a cljthon function to reverse only the vowels of a given string (where y is not a vowel)."
  [str1]
  (let [s (or str1 "")
        vowel? (set "aeiouAEIOU")
        vowels (reverse (filter vowel? s))]
    (apply str
           (first
            (reduce (fn [[result remaining-vowels] ch]
                      (if (vowel? ch)
                        [(conj result (first remaining-vowels)) (rest remaining-vowels)]
                        [(conj result ch) remaining-vowels]))
                    [[] vowels]
                    s)))))