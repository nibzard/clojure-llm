(defn check_str
  "Write a function to check whether the given string is starting with a vowel or not using regex."
  [string]
  (boolean
   (and (string? string)
        (re-find #"(?i)^[aeiou]" string))))