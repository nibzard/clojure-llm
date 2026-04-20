(defn dif_Square
  "Check whether the given number can be represented as the difference of two squares or not.
   A number can be expressed as a difference of two squares (a^2 - b^2) if and only if
   it is not of the form 2*(2k+1) (i.e., not 2 times an odd number)."
  [n]
  (cond
    (nil? n) false
    (not (integer? n)) false
    (neg? n) false
    (zero? n) true
    :else (not (and (odd? (quot n 2)) (even? n)))))