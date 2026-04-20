(defn first_repeated_char
  "Write a cljthon function to find the first repeated character in a given string."
  [str1]
  (when (string? str1)
    (loop [seen #{}
           [ch & rest] (seq str1)]
      (cond
        (nil ch) nil
        (contains? seen ch) ch
        :else (recur (conj seen ch) rest)))))