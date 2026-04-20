(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [start (long (or l 0))
        end   (long (or r 0))
        x     (long (or n 0))]
    (if (or (<= start 0) (< end start))
      false
      (let [width (inc (- end start))
            mask  (bit-shift-left (dec (bit-shift-left 1 width)) (dec start))]
        (zero? (bit-and x mask))))))