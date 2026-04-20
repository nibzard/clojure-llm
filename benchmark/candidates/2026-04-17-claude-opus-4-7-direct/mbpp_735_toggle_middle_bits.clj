(defn toggle_middle_bits
  "Write a cljthon function to toggle bits of the number except the first and the last bit."
  [n]
  (if (<= n 0)
    n
    (let [msb-pos (-> n Integer/toBinaryString count dec)
          mask (-> (bit-shift-left 1 msb-pos)
                   dec
                   (bit-shift-right 1))]
      (bit-xor n mask))))