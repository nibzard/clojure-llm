(defn string_sequence
  "Return a string containing space-delimited numbers starting from 0 upto n inclusive."
  [n]
  (clojure.string/join " " (map str (range (inc n)))))