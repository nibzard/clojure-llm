(defn is_lower
  "	Write a cljthon function to convert the given string to lower case."
  [string]
  (when string
    (.toLowerCase ^String string)))