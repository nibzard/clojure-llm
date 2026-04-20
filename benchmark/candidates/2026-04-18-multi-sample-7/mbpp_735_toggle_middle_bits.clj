(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (if (or (nil? n) (< n 4))
    n
    (let [bit-len (loop [x n
                         c 0]
                    (if (zero? x)
                      c
                      (recur (bit-shift-right x 1) (inc c))))
          mask (if (<= bit-len 2)
                 0
                 (bit-shift-left (dec (bit-shift-left 1 (- bit-len 2))) 1))]
      (bit-xor n mask))))