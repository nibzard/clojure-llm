(defn get_Char
  "	Write a cljthon function to find the character made by adding the ASCII value of all the characters of the given string modulo 26."
  [strr]
  (char (+ (int \A)
           (mod (reduce + (map int strr)) 26))))