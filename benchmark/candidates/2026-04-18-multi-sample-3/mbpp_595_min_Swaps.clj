(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [s1 (or str1 "")
        s2 (or str2 "")]
    (if (not= (count s1) (count s2))
      -1
      (let [freq1 (frequencies s1)
            freq2 (frequencies s2)]
        (if (or (not= (get freq1 \0 0) (get freq2 \0 0))
                (not= (get freq1 \1 0) (get freq2 \1 0)))
          -1
          (let [mismatches (reduce (fn [cnt [c1 c2]]
                                     (if (not= c1 c2) (inc cnt) cnt))
                                   0
                                   (map vector s1 s2))]
            (/ mismatches 2)))))))