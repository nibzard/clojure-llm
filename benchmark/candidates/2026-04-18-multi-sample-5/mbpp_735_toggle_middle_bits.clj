(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (if (or (nil? n) (< n 2))
    n
    (let [bit-len (loop [x n
                         len 0]
                    (if (zero? x)
                      len
                      (recur (bit-shift-right x 1) (inc len))))]
      (if (<= bit-len 2)
        n
        (let [mask (- (bit-shift-left 1 (dec bit-len)) 2)]
          (bit-xor n mask))))))