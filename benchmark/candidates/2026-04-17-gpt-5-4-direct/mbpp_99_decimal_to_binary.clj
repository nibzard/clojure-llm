(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (cond
    (nil? n) nil
    (zero? n) "0"
    :else
    (let [sign (if (neg? n) "-" "")
          n (Math/abs (long n))]
      (str sign
           (loop [x n
                  acc ()]
             (if (zero? x)
               (apply str acc)
               (recur (quot x 2) (conj acc (char (+ 48 (rem x 2))))))))))