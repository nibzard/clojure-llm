(defn toggle_middle_bits [n]
  (if (zero? n)
    0
    (let [h (Long/highestOneBit n)
          mask (bit-and (dec h) -2)]
      (bit-xor n mask))))