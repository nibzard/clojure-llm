(defn count
  "	Write a cljthon function to count true booleans in the given list."
  [lst]
  (clojure.core/count (filter true? lst)))