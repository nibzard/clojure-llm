(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (let [mask (fn [n]
               (loop [x n
                      m 0]
                 (if (zero? x)
                   m
                   (recur (quot x 2) (bit-or m 1)))))]
    (if (zero? n)
      0
      (let [m (mask n)]
        (bit-xor n (bit-and m (dec m)))))))