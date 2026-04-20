(defn toggle_string
  "	Write a function to toggle the case of all characters in a string."
  [string]
  (apply str (map #(cond
                     (Character/isUpperCase %) (Character/toLowerCase %)
                     (Character/isLowerCase %) (Character/toUpperCase %)
                     :else %)
                  string)))