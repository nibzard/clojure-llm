(defn even_binomial_Coeff_Sum
  "Write a clojure function that takes in a positive integer n and finds the sum of even index binomial coefficients."
  [n]
  (if (pos? n)
    (bit-shift-left 1N (dec n))
    0))