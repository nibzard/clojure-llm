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
                     (cond
                       (<= (int \a) (int c) (int \z))
                       (char (+ (int \a) (mod (+ (- (int c) (int \a)) 4) 26)))

                       (<= (int \A) (int c) (int \Z))
                       (char (+ (int \A) (mod (+ (- (int c) (int \A)) 4) 26)))

                       :else
                       c))]
    (apply str (map shift-char (or s "")))))