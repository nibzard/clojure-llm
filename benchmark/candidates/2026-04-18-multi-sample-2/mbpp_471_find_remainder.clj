(defn find_remainder
  "	Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (let [mod-base (bigint n)]
    (cond
      (nil? arr) nil
      (empty? arr) (mod 1 mod-base)
      :else
      (reduce (fn [acc x]
                (mod (* acc (bigint x)) mod-base))
              1
              arr))))