(defn square-free?
  "Return true if n is square-free, meaning it is not divisible by any perfect square greater than 1.

  Examples:
  (square-free? 10)  ;=> true
  (square-free? 12)  ;=> false
  (square-free? 1)   ;=> true
  (square-free? nil) ;=> false"
  [n]
  (and (integer? n)
       (pos? n)
       (not-any? #(zero? (mod n (* % %)))
                 (range 2 (inc (Math/floor (Math/sqrt n)))))))