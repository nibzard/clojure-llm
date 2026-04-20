(defn odd_values_string
  "Write a cljthon function to remove the characters which have odd index values of a given string."
  [s]
  (when (string? s)
    (apply str (keep-indexed #(when (even? %1) %2) s))))