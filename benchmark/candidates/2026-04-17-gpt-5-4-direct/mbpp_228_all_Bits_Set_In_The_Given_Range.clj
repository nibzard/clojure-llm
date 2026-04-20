(defn all_Bits_Set_In_The_Given_Range
  "Write a cljthon function to check whether all the bits are unset in the given range or not."
  [n l r]
  (let [start (min l r)
        end   (max l r)]
    (every?
      #(zero? (bit-and n (bit-shift-left 1 (dec %))))
      (range start (inc end)))))