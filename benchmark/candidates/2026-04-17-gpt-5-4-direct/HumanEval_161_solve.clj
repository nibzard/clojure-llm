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
  (let [s (or s "")]
    (if (some #(Character/isLetter ^char %) s)
      (apply str
             (map (fn [ch]
                    (cond
                      (Character/isLowerCase ^char ch) (Character/toUpperCase ^char ch)
                      (Character/isUpperCase ^char ch) (Character/toLowerCase ^char ch)
                      :else ch))
                  s))
      (apply str (reverse s)))))