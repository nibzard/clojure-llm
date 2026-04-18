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
  (let [x (bigint x)
        base (bigint base)]
    (cond
      (zero? x) "0"
      (nil? x) nil
      (nil? base) nil
      (<= base 1) nil
      :else
      (loop [n x
             acc []]
        (if (zero? n)
          (apply str acc)
          (recur (quot n base) (conj acc (mod n base))))))))