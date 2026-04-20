(defn replace_char
  "	Write a function to replace characters in a string."
  [str1 ch newch]
  (when (some? str1)
    (let [target (first (str ch))
          replacement (first (str newch))]
      (apply str
             (map #(if (= % target) replacement %)
                  str1)))))