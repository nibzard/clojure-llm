(defn capital_words_spaces
  "Write a function to put spaces between words starting with capital letters in a given string."
  [str1]
  (if (or (nil? str1) (= "" str1))
    str1
    (clojure.string/trim
     (apply str
            (map-indexed
             (fn [idx ch]
               (if (and (pos? idx) (Character/isUpperCase ^Character ch))
                 (str " " ch)
                 (str ch)))
             str1)))))