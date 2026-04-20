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
  (loop [n (bigint x)
         acc ""]
    (if (zero? n)
      (if (empty? acc) "0" acc)
      (recur (quot n base) (str (mod n base) acc)))))