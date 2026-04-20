(defn flip_case
  " For a given string, flip lowercase characters to uppercase and uppercase to lowercase.
  >>> (flip_case \"Hello\")
  \"hELLO\""
  [string]
  (when string
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isLowerCase ch) (Character/toUpperCase ch)
                    (Character/isUpperCase ch) (Character/toLowerCase ch)
                    :else ch))
                string))))