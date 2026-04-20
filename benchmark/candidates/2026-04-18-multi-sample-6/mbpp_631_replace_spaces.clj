(defn replace_spaces
  "	Write a function to replace whitespaces with an underscore and vice versa in a given string."
  [text]
  (when-not (nil? text)
    (apply str
           (map (fn [ch]
                  (cond
                    (= ch \_) \space
                    (Character/isWhitespace ^char ch) \_
                    :else ch))
                text))))