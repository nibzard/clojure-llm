(defn find_remainder
  "	Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (let [m (long n)]
    (if (or (nil? arr) (empty? arr))
      0
      (reduce (fn [acc x]
                (mod (* acc (long x)) m))
              1
              arr))))