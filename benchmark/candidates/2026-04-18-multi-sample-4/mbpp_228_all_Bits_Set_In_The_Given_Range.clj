(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [start (long (or l 0))
        end   (long (or r 0))
        n     (long (or n 0))]
    (if (> start end)
      true
      (every?
       (fn [pos]
         (zero? (bit-and n (bit-shift-left 1 (dec pos)))))
       (range start (inc end))))))