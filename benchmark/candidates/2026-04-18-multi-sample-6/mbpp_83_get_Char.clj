(defn get_Char
  "	Write a cljthon function to find the character made by adding the ASCII value of all the characters of the given string modulo 26."
  [strr]
  (let [s (or strr "")
        total (reduce + 0 (map int s))]
    (char (+ (int \A) (mod total 26)))))