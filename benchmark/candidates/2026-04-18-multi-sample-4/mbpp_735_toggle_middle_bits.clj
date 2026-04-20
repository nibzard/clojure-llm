(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (let [n (long (or n 0))]
    (if (< n 4)
      n
      (let [bit-len (- 64 (Long/numberOfLeadingZeros n))
            mask (bit-shift-left (- (bit-shift-left 1 (- bit-len 2)) 1) 1)]
        (bit-xor n mask)))))