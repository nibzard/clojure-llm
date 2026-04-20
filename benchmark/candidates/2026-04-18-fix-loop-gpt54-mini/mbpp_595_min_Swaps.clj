(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [n1 (count str1)
        n2 (count str2)]
    (if (not= n1 n2)
      -1
      (let [c1-0 (count (filter #(= % \0) str1))
            c2-0 (count (filter #(= % \0) str2))
            c1-1 (count (filter #(= % \1) str1))
            c2-1 (count (filter #(= % \1) str2))]
        (if (or (not= c1-0 c2-0) (not= c1-1 c2-1))
          -1
          (let [mismatches (count (filter true? (map not= str1 str2)))]
            (/ mismatches 2)))))))