(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (cond
    (nil? n) nil
    (zero? n) nil
    (nil? arr) 1
    (empty? arr) 1
    :else (reduce (fn [acc x] (mod (* acc x) n)) 1 arr)))