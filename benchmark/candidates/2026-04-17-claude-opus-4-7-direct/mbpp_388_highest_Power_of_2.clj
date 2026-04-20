(defn highest-Power-of-2
  "Write a cljthon function to find the highest power of 2 that is less than or equal to n."
  [n]
  (if (<= n 0)
    0
    (bit-shift-left 1 (bit-length (dec n)))))