(defn string_to_list
  "	Write a function to convert a string to a list of strings split on the space character."
  [string]
  (if (nil? string)
    '()
    (clojure.string/split string #" ")))