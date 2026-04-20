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
  (cond
    (nil? x) nil
    (nil? base) nil
    (= x 0) "0"
    :else
    (loop [n x
           digits ()]
      (if (zero? n)
        (apply str digits)
        (recur (quot n base)
               (conj digits (str (mod n base))))))))