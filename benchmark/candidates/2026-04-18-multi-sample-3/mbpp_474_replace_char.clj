(defn replace_char
  "	Write a function to replace characters in a string."
  [str1 ch newch]
  (when (some? str1)
    (apply str (map #(if (= % ch) newch %) str1))))