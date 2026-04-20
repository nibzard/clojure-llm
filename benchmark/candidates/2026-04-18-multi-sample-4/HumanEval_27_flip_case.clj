(defn flip_case
  " For a given string, flip lowercase characters to uppercase and uppercase to lowercase.
  >>> (flip_case \"Hello\")
  \"hELLO\""
  [string]
  (when (some? string)
    (apply str
           (map (fn [^Character ch]
                  (cond
                    (Character/isUpperCase ch) (Character/toLowerCase ch)
                    (Character/isLowerCase ch) (Character/toUpperCase ch)
                    :else ch))
                string))))