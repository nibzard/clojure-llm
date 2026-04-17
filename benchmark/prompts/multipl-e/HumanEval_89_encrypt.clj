(defn encrypt
  "Create a function encrypt that takes a string as an argument and
  returns a string encrypted with the alphabet being rotated. 
  The alphabet should be rotated in a manner such that the letters 
  shift down by two multiplied to two places.
  For example:
  >>> (encrypt "hi")
  "lm"
  >>> (encrypt "asdfghjkl")
  "ewhjklnop"
  >>> (encrypt "gf")
  "kj"
  >>> (encrypt "et")
  "ix""
  [s]
  