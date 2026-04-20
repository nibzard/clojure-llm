(defn find_Parity
  "Write a clojure function to find whether the parity (number of set bits) of a given number is odd."
  [x]
  (odd? (Long/bitCount (long x))))