(defn even_binomial_Coeff_Sum
  "Takes in a positive integer n and finds the sum of even index binomial coefficients."
  [n]
  (bit-shift-left 1 (dec n)))