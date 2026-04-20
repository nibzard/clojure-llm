(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (if (zero? n)
    "0"
    (loop [n n
           bits ()]
      (if (zero? n)
        (apply str bits)
        (recur (quot n 2) (conj bits (char (+ (int \0) (mod n 2)))))))))