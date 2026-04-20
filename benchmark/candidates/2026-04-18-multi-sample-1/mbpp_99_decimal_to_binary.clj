(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (let [n (or n 0)]
    (if (zero? n)
      "0"
      (loop [x n
             acc ()]
        (if (zero? x)
          (apply str acc)
          (recur (quot x 2) (conj acc (char (+ (mod x 2) (int \0))))))))))