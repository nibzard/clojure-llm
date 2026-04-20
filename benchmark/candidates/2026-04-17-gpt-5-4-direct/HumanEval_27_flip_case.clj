(defn flip_case
  " For a given string, flip lowercase characters to uppercase and uppercase to lowercase.
  >>> (flip_case \"Hello\")
  \"hELLO\""
  [string]
  (when (some? string)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isUpperCase ^Character ch) (Character/toLowerCase ^Character ch)
                    (Character/isLowerCase ^Character ch) (Character/toUpperCase ^Character ch)
                    :else ch))
                string))))