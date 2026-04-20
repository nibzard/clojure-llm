(defn solve
  "You are given a string s.
  if s[i] is a letter, reverse its case from lower to upper or vise versa, 
  otherwise keep it as it is.
  If the string contains no letters, reverse the string.
  The function should return the resulted string.
  Examples
  >>> (solve \"1234\")
  \"4321\"
  >>> (solve \"ab\")
  \"AB\"
  >>> (solve \"#a@C\")
  \"#A@c\""
  [s]
  (let [has-letter? (some #(Character/isLetter ^char %) s)]
    (if has-letter?
      (apply str
             (map (fn [^char c]
                    (cond
                      (Character/isLowerCase c) (Character/toUpperCase c)
                      (Character/isUpperCase c) (Character/toLowerCase c)
                      :else c))
                  s))
      (apply str (reverse s)))))