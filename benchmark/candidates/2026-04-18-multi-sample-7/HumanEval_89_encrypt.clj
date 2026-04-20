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
  (let [shift 4
        a (int \a)
        z (int \z)]
    (apply str
           (map (fn [ch]
                  (let [c (int ch)]
                    (if (<= a c z)
                      (char (+ a (mod (+ (- c a) shift) 26)))
                      ch)))
                (or s "")))))