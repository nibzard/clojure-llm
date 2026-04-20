(defn filter_integers
  "Filter given list of any cljthon values only for integers"
  [values]
  (filter integer? values))