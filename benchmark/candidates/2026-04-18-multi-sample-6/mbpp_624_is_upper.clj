(defn is_upper
  "	Write a cljthon function to convert a given string to uppercase."
  [string]
  (if (nil? string)
    nil
    (clojure.string/upper-case (str string))))