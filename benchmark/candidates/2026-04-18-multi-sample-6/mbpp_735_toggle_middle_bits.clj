(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (if (or (nil? n) (< n 2))
    n
    (let [bits (Integer/toBinaryString n)
          len  (count bits)]
      (if (<= len 2)
        n
        (let [mask (bit-shift-left (- (bit-shift-left 1 (- len 2)) 1) 1)]
          (bit-xor n mask))))))