(defn is_lower
  "	Write a cljthon function to convert the given string to lower case."
  [string]
  (when-not (nil? string)
    (clojure.string/lower-case (str string))))