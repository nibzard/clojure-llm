(defn solve
  "You are given a string s.
  if s[i] is a letter, reverse its case from lower to upper or vise versa, 
  otherwise keep it as it is.
  If the string contains no letters, reverse the string.
  The function should return the resulted string."
  [s]
  (let [swapped (apply str (map #(if (Character/isLetter %)
                                   (Character/toString (Character/toUpperCase %))
                                   %)
                               s))]
    (if (re-matches #"[^a-zA-Z]*" s)
      (apply str (reverse swapped))
      swapped)))