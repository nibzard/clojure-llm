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
  (let [x (long x)
        base (long base)]
    (cond
      (neg? x) (str "-" (change_base (- x) base))
      (<= base 1) (throw (IllegalArgumentException. "base must be greater than 1"))
      (zero? x) "0"
      :else
      (loop [n x
             digits ()]
        (if (zero? n)
          (apply str digits)
          (recur (quot n base) (cons (str (mod n base)) digits)))))))