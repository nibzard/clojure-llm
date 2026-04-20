(defn remove_odd
  "Write a function to remove odd characters in a string."
  [str1]
  (when (string? str1)
    (apply str (keep-indexed #(when (even? %1) %2) str1))))