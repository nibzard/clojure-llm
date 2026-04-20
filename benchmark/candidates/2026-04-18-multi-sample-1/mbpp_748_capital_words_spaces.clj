(defn capital_words_spaces
  "	Write a function to put spaces between words starting with capital letters in a given string."
  [str1]
  (let [s (or str1 "")]
    (apply str
           (mapcat (fn [[idx ch]]
                     (if (and (pos? idx) (Character/isUpperCase ^Character ch))
                       [\space ch]
                       [ch]))
                   (map-indexed vector s)))))