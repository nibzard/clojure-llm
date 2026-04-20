(defn toggle_string
  "	Write a function to toggle the case of all characters in a string."
  [string]
  (when-not (nil? string)
    (apply str
           (map (fn [ch]
                  (cond
                    (Character/isUpperCase ch) (Character/toLowerCase ch)
                    (Character/isLowerCase ch) (Character/toUpperCase ch)
                    :else ch))
                string))))