(defn first_repeated_char
  "Write a cljthon function to find the first repeated character in a given string."
  [str1]
  (when (seq str1)
    (loop [chars (seq str1)
           seen #{}]
      (when-let [c (first chars)]
        (if (contains? seen c)
          c
          (recur (next chars) (conj seen c)))))))