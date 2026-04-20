(defn replace_spaces
  "	Write a function to replace whitespaces with an underscore and vice versa in a given string."
  [text]
  (when (some? text)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isWhitespace ch) \_
                    (= ch \_) \space
                    :else ch))
                text))))