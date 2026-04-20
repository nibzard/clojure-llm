(defn replace_char
  "	Write a function to replace characters in a string."
  [str1 ch newch]
  (let [s (or str1 "")
        old-char (if (char? ch) ch (first (str ch)))
        new-char (if (char? newch) newch (first (str newch)))]
    (apply str (map #(if (= % old-char) new-char %) s))))