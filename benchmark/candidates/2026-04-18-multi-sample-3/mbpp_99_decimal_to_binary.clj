(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (if (zero? n)
    "0"
    (loop [n n
           acc ()]
      (if (zero? n)
        (apply str acc)
        (recur (quot n 2) (conj acc (char (+ (int \0) (mod n 2)))))))))