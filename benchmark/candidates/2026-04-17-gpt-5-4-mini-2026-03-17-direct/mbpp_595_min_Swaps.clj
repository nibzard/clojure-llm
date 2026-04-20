(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [a (or str1 "")
        b (or str2 "")]
    (if (not= (count a) (count b))
      -1
      (let [pairs (map vector a b)
            counts (frequencies (map (fn [[x y]] [x y]) pairs))
            a0b1 (get counts [\0 \1] 0)
            a1b0 (get counts [\1 \0] 0)]
        (if (odd? (+ a0b1 a1b0))
          -1
          (+ (quot a0b1 2)
             (quot a1b0 2)
             (if (and (odd? a0b1) (odd? a1b0)) 2 0))))))