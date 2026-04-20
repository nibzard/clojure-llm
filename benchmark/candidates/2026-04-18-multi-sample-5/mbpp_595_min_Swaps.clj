(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [s1 (or str1 "")
        s2 (or str2 "")]
    (if (not= (count s1) (count s2))
      -1
      (let [mismatches (map vector s1 s2)
            xy (count (filter #(= % [\0 \1]) mismatches))
            yx (count (filter #(= % [\1 \0]) mismatches))]
        (if (odd? (+ xy yx))
          -1
          (+ (quot xy 2)
             (quot yx 2)
             (* 2 (mod xy 2))))))))