(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (let [n (or n 0)]
    (if (< n 5)
      n
      (let [bit-len (Long/size n)
            msb-pos (- bit-len 1 (Long/numberOfLeadingZeros n))
            mask (bit-shift-left (- (bit-shift-left 1 (dec msb-pos)) 1) 1)]
        (bit-xor n mask)))))