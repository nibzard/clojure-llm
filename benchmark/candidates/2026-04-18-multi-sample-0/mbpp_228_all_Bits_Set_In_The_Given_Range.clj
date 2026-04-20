(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [l (long l)
        r (long r)]
    (if (or (nil? n) (nil? l) (nil? r) (> l r) (<= l 0))
      false
      (let [width (inc (- r l))
            mask  (bit-shift-left (dec (bit-shift-left 1 width)) (dec l))]
        (zero? (bit-and (long n) mask))))))