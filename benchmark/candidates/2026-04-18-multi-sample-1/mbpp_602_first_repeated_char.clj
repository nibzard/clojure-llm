(defn first_repeated_char
  "	Write a cljthon function to find the first repeated character in a given string."
  [str1]
  (when (seq str1)
    (loop [seen #{}
           chars (seq str1)]
      (when-let [ch (first chars)]
        (if (contains? seen ch)
          ch
          (recur (conj seen ch) (next chars)))))))