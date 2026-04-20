(defn all_Bits_Set_In_The_Given_Range
  "Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [num-bits (inc (- r l))
        mask (bit-shift-left (dec (bit-shift-left 1 num-bits)) (dec l))]
    (= (bit-and n mask) mask)))