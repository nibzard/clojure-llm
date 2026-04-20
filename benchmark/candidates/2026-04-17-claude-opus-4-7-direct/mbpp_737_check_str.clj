(defn check_str
  "Write a function to check whether the given string is starting with a vowel or not using regex."
  [string]
  (and (string? string)
       (not (empty? string))
       (boolean (re-matches #"(?i)^[aeiou].*" string))))