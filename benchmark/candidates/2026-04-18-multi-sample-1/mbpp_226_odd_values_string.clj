(defn odd_values_string
  "	Write a cljthon function to remove the characters which have odd index values of a given string."
  [str]
  (apply str (keep-indexed (fn [idx ch] (when (even? idx) ch)) str)))