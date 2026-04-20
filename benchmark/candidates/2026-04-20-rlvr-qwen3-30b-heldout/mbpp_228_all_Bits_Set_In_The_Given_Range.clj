(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [n (long (or n 0))
        l (long (or l 1))
        r (long (or r 0))
        mask (bit-and
               (bit-shift-left -1 (dec r))
               (bit-not (bit-shift-left -1 (dec (max 0 (dec l))))))]
    (zero? (bit-and n mask))))