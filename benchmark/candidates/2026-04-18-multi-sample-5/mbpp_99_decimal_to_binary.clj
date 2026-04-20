(defn decimal_to_binary
  "	Write a function to convert the given decimal number to its binary equivalent, represented as a string with no leading zeros."
  [n]
  (cond
    (nil? n) nil
    (zero? n) "0"
    :else
    (let [negative? (neg? n)
          n' (if negative? (- n) n)
          bits (loop [x n'
                      acc ()]
                 (if (zero? x)
                   acc
                   (recur (quot x 2) (conj acc (if (odd? x) \1 \0)))))
          s (apply str bits)]
      (if negative?
        (str "-" s)
        s))))