(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take n arr)]
    (reduce
      (fn [acc bit]
        (let [ones (count (filter #(bit-test % bit) xs))
              zeros (- n ones)]
          (+ acc (* ones zeros (bit-shift-left 1 bit)))))
      0
      (range 0 31))))