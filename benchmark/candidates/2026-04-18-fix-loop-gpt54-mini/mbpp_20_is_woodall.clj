(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (boolean
   (and (integer? x)
        (pos? x)
        (some true?
              (for [n (range 1 1000)]
                (= x (* n (bit-shift-left 1 n))))))))