(defn replace_spaces
  "Write a function to replace whitespaces with an underscore and vice versa in a given string."
  [text]
  (clojure.string/escape text {\space \_ \_ \space}))