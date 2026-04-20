(defn first_repeated_char
  "Write a cljthon function to find the first repeated character in a given string."
  [str1]
  (loop [seen #{}
         [c & cs] str1]
    (when c
      (if (seen c)
        c
        (recur (conj seen c) cs)))))