(defn toggle_string
  "Write a function to toggle the case of all characters in a string."
  [string]
  (when string
    (apply str (map #(if (Character/isUpperCase %)
                       (Character/toLowerCase %)
                       (Character/toUpperCase %))
                    string))))