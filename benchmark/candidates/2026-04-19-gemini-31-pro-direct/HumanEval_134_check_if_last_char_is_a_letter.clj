(defn check_if_last_char_is_a_letter
  [txt]
  (boolean (re-find #"(?:\s|^)[A-Za-z]$" txt)))