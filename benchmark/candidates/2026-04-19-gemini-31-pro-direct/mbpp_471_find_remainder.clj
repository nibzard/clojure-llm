(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (reduce (fn [acc x] (mod (* acc x) n)) (mod 1 n) arr))