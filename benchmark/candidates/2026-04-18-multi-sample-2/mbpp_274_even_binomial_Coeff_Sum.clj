(defn even_binomial_Coeff_Sum
  "	Write a cljthon function that takes in a positive integer n and finds the sum of even index binomial coefficients."
  [n]
  (if (and (integer? n) (pos? n))
    (bit-shift-left 1 (dec n))
    0))