(defn is_lower
  "Write a cljthon function to convert the given string to lower case."
  [string]
  (if (nil? string)
    nil
    (clojure.string/lower-case (str string))))