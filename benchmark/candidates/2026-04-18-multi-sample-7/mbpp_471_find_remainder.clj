(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (cond
    (or (nil? arr) (empty? arr) (nil? n)) nil
    (zero? n) nil
    :else (mod (reduce * 1 arr) n)))