(defn encrypt
  "Create a function encrypt that takes a string as an argument and
  returns a string encrypted with the alphabet being rotated. 
  The alphabet should be rotated in a manner such that the letters 
  shift down by two multiplied to two places.
  For example:
  >>> (encrypt \"hi\")
  \"lm\"
  >>> (encrypt \"asdfghjkl\")
  \"ewhjklnop\"
  >>> (encrypt \"gf\")
  \"kj\"
  >>> (encrypt \"et\")
  \"ix\""
  [s]
  (let [shift-char (fn [c]
                     (if (Character/isLowerCase ^char c)
                       (char (+ (int \a) (mod (+ (- (int c) (int \a)) 4) 26)))
                       c))]
    (apply str (map shift-char (or s "")))))