(defn flip_case
  "For a given string, flip lowercase characters to uppercase and uppercase to lowercase."
  [string]
  (when string
    (apply str (map #(if (Character/isUpperCase %)
                       (Character/toLowerCase %)
                       (Character/toUpperCase %))
                    string))))