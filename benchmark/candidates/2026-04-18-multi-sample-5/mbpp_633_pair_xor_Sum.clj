(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take (or n 0) (or arr []))
        freqs (reduce (fn [m x]
                        (reduce (fn [acc bit]
                                  (update acc bit (fnil + 0) (bit-and 1 (bit-shift-right x bit))))
                                m
                                (range 0 32)))
                      {}
                      xs)]
    (reduce (fn [sum [bit ones]]
              (let [zeros (- n ones)]
                (+ sum (* (bit-shift-left 1 bit) ones zeros))))
            0
            freqs)))