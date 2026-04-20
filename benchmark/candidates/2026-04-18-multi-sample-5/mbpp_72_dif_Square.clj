(defn dif_Square
  "	Write a cljthon function to check whether the given number can be represented as the difference of two squares or not."
  [n]
  (let [n (when (number? n) (bigint n))]
    (boolean
     (and n
          (not (neg? n))
          (not= 2 (mod n 4))))))