(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (if (or (nil? n) (zero? n))
    nil
    (mod (reduce * 1 (or arr [])) n)))