(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [mask (bit-shift-left 1 (inc r))
        range-mask (bit-shift-left 1 l)
        relevant-bits (bit-and n (bit-and mask (bit-not range-mask)))]
    (zero? relevant-bits)))