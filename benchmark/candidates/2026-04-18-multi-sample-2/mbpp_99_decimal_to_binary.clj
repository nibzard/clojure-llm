(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (cond
    (nil? n) nil
    (zero? n) "0"
    :else
    (let [sign (if (neg? n) "-" "")
          x (bigint (if (neg? n) (- n) n))]
      (str sign
           (loop [v x
                  bits ()]
             (if (zero? v)
               (apply str bits)
               (recur (quot v 2)
                      (conj bits (char (+ 48 (int (mod v 2))))))))))))