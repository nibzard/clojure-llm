(defn change_base
  "Change numerical base of input number x to base.
  return string representation after the conversion.
  base numbers are less than 10."
  [x base]
  (letfn [(digits [n]
            (if (< n base)
              (str n)
              (str (digits (quot n base)) (mod n base))))]
    (cond
      (nil? x) nil
      (nil? base) nil
      (zero? x) "0"
      :else (digits x))))