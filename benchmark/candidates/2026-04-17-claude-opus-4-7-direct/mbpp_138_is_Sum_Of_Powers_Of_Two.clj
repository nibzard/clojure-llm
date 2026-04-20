(defn is-Sum-Of-Powers-Of-Two
  "Check whether the given number can be represented as sum of non-zero powers of 2 or not."
  [n]
  (and (integer? n)
       (pos? n)
       (not (zero? (bit-and n (dec n))))))