(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (let [mask (fn [x]
               (loop [i 1
                      m 0]
                 (if (>= i x)
                   m
                   (recur (inc i) (bit-or m (bit-shift-left 1 i))))))]
    (if (zero? n)
      0
      (bit-xor n (mask (bit-count n))))))