(defn replace_spaces
  "	Write a function to replace whitespaces with an underscore and vice versa in a given string."
  [text]
  (when (some? text)
    (apply str
           (map (fn [ch]
                  (case ch
                    \space \_
                    \_ \space
                    ch))
                text))))