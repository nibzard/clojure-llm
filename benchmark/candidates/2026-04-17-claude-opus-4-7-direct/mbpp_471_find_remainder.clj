(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (if (empty? arr)
    0
    (mod (reduce *' 1 arr) n)))