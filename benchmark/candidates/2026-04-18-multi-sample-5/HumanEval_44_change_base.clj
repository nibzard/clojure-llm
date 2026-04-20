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
    (zero? x) "0"
    :else
    (let [negative? (neg? x)
          n (Math/abs (long x))
          digits (loop [n n
                        acc ()]
                   (if (zero? n)
                     acc
                     (recur (quot n base) (conj acc (str (mod n base))))))]
      (str (when negative? "-")
           (apply str digits)))))