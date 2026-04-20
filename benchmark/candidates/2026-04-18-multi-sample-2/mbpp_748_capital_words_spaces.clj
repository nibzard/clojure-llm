(defn capital_words_spaces
  "	Write a function to put spaces between words starting with capital letters in a given string."
  [str1]
  (if (or (nil? str1) (empty? str1))
    str1
    (clojure.string/trim
     (apply str
            (map-indexed
             (fn [i ch]
               (if (and (pos? i) (Character/isUpperCase ^Character ch))
                 (str " " ch)
                 (str ch)))
             str1)))))