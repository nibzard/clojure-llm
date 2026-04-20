(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (if (or (nil? n) (< n 0) (< n 4))
    n
    (let [msb-pos (dec (Long/size (Long/toBinaryString n)))
          mask    (bit-shift-left (dec (bit-shift-left 1 (dec msb-pos))) 1)]
      (bit-xor n mask))))