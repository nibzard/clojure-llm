(defn get_Char
  "Write a cljthon function to find the character made by adding the ASCII value of all the characters of the given string modulo 26."
  [strr]
  (when (seq strr)
    (let [total (reduce + 0 (map int strr))
          result (mod total 26)]
      (char (+ result (int \a))))))