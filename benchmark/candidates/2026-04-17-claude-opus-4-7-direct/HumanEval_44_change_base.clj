(defn change_base
  "Change numerical base of input number x to base.
  return string representation after the conversion.
  base numbers are less than 10."
  [x base]
  (cond
    (zero? x) "0"
    (pos? x) (loop [n x
                    acc []]
               (if (zero? n)
                 (apply str (reverse acc))
                 (recur (quot n base) (conj acc (mod n base)))))
    :else (throw (IllegalArgumentException. "Input number must be non-negative"))))