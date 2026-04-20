(defn replace_char
  "	Write a function to replace characters in a string."
  [str1 ch newch]
  (when (some? str1)
    (let [target (if (char? ch) ch (first (str ch)))
          replacement (if (char? newch) newch (first (str newch)))]
      (apply str (map #(if (= % target) replacement %) str1)))))