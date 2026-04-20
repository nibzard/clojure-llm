(defn decimal_to_binary
  "Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (cond
    (nil? n) nil
    (zero? n) "0"
    :else (let [neg? (neg? n)
                s (Long/toBinaryString (Math/abs (long n)))]
            (if neg?
              (str "-" s)
              s))))