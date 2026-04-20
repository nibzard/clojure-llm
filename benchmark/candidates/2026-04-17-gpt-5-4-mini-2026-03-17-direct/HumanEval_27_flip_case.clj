(defn flip_case
  "For a given string, flip lowercase characters to uppercase and uppercase to lowercase."
  [string]
  (apply str
         (map (fn [ch]
                (cond
                  (Character/isUpperCase ch) (Character/toLowerCase ch)
                  (Character/isLowerCase ch) (Character/toUpperCase ch)
                  :else ch))
              string)))