(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (when (seq s)
    (apply str
           (interpose " "
                      (reverse (re-seq #"[^\s]+" s))))))