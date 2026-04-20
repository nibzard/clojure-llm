(defn flip_case
  " For a given string, flip lowercase characters to uppercase and uppercase to lowercase.
  >>> (flip_case \"Hello\")
  \"hELLO\""
  [string]
  (when (some? string)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isUpperCase ^char ch) (Character/toLowerCase ^char ch)
                    (Character/isLowerCase ^char ch) (Character/toUpperCase ^char ch)
                    :else ch))
                string))))