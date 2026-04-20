(defn reverse_vowels
  "	Write a cljthon function to reverse only the vowels of a given string (where y is not a vowel)."
  [str1]
  (let [s (or str1 "")
        vowel? (set "aeiouAEIOU")
        vowels (reverse (filter vowel? s))]
    (apply str
           (second
            (reduce (fn [[vs acc] ch]
                      (if (vowel? ch)
                        [(rest vs) (conj acc (first vs))]
                        [vs (conj acc ch)]))
                    [vowels []]
                    s)))))