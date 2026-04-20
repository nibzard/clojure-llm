(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (cond
    (nil? n) nil
    (zero? n) "0"
    (neg? n) (str "-" (decimal_to_binary (- n)))
    :else
    (loop [x n
           acc ()]
      (if (zero? x)
        (apply str acc)
        (recur (quot x 2) (conj acc (char (+ (int \0) (mod x 2)))))))))