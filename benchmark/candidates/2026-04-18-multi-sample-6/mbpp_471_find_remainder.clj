(defn find_remainder
  "Write a cljthon function to find the product of the vector multiplication modulo n."
  [arr n]
  (let [nums (or arr [])]
    (cond
      (nil? n) nil
      (zero? n) nil
      (empty? nums) 1
      :else (mod (reduce * 1 nums) n))))