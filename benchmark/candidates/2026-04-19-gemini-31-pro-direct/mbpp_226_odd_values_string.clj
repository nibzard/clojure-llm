(defn odd_values_string
  "Write a cljthon function to remove the characters which have odd index values of a given string."
  [str]
  (apply clojure.core/str (take-nth 2 str)))