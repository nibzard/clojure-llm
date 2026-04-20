(defn replace_char
  "	Write a function to replace characters in a string."
  [str1 ch newch]
  (when (some? str1)
    (let [old-char (if (char? ch) ch (first (str ch)))
          new-char (if (char? newch) newch (first (str newch)))]
      (apply str (map #(if (= % old-char) new-char %) str1)))))