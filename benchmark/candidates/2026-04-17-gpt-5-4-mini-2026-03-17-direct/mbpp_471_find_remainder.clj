(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (if (or (nil? arr) (empty? arr) (nil? n) (zero? n))
    0
    (mod (reduce *' 1 arr) n)))