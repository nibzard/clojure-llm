(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [n (long (or n 0))
        l (long (or l 1))
        r (long (or r 0))
        mask (bit-shift-left (dec (bit-shift-left 1 (max 0 (min r l)))) (dec (max 0 l)))]
    (zero? (bit-and n mask))))