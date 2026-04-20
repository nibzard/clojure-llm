(defn flip_case
  " For a given string, flip lowercase characters to uppercase and uppercase to lowercase.
  >>> (flip_case \"Hello\")
  \"hELLO\""
  [string]
  (when-not (nil? string)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isLowerCase ^char ch) (Character/toUpperCase ^char ch)
                    (Character/isUpperCase ^char ch) (Character/toLowerCase ^char ch)
                    :else ch))
                string))))