(defn all_Bits_Set_In_The_Given_Range
  "	Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [start (long (min l r))
        end   (long (max l r))]
    (if (or (nil? n) (nil? l) (nil? r) (<= start 0))
      false
      (every?
       #(zero? (bit-and (long n) (bit-shift-left 1 (dec %))))
       (range start (inc end))))))