(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-except-first-last-bits/"
  [n]
  (let [n (long (or n 0))
        bit-count (if (zero? n) 1 (long (Math/floor (/ (Math/log (inc n)) (Math/log 2)))))
        mask (if (<= bit-count 2)
               0
               (bit-and -2 (bit-shift-left -2 (dec bit-count))))]
    (bit-xor n mask)))