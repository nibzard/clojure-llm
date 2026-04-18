(defn change_base
  "Change numerical base of input number x to base.
  return string representation after the conversion.
  base numbers are less than 10.
  >>> (change_base 8 3)
  \"22\"
  >>> (change_base 8 2)
  \"1000\"
  >>> (change_base 7 2)
  \"111\""
  [x base]
  (let [x (bigint (or x 0))
        base (bigint (or base 10))]
    (if (zero? x)
      "0"
      (loop [n x
             b base
             acc []]
        (if (zero? n)
          (apply str acc)
          (recur (quot n b) b (conj acc (mod n b))))))))