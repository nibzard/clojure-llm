(defn is_upper
  "	Write a cljthon function to convert a given string to uppercase."
  [string]
  (when (some? string)
    (.toUpperCase ^String string)))