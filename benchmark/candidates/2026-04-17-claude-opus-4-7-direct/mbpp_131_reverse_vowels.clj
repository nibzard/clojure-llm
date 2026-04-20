(defn reverse_vowels
  "Write a cljthon function to reverse only the vowels of a given string (where y is not a vowel)."
  [str1]
  (if (nil? str1)
    nil
    (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
          chars (vec str1)
          vowel-indices (keep-indexed #(when (vowels %2) %1) chars)
          vowel-chars (map chars vowel-indices)
          reversed-vowels (vec (reverse vowel-chars))]
      (apply str 
             (map-indexed (fn [idx char]
                            (if (vowels char)
                              (get reversed-vowels (.indexOf vowel-indices idx))
                              char))
                          chars)))))