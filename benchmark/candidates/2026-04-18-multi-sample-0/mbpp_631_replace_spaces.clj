(defn replace_spaces
  "	Write a function to replace whitespaces with an underscore and vice versa in a given string."
  [text]
  (when-not (nil? text)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isWhitespace ^Character ch) \_
                    (= ch \_) \space
                    :else ch))
                text))))