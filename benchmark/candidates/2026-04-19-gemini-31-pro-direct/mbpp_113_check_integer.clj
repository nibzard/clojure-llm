(defn check_integer
  "Write a function to check if a string represents an integer or not."
  [text]
  (boolean (and (string? text) (re-matches #"[-+]?\d+" text))))