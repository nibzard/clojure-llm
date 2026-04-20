(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (let [m (bigint n)]
    (cond
      (nil? arr) nil
      (zero? m) nil
      (empty? arr) 1
      :else (reduce (fn [acc x]
                      (mod (* acc (bigint x)) m))
                    1
                    arr))))