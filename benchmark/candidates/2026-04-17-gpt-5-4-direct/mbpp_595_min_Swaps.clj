(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [s1 (or str1 "")
        s2 (or str2 "")]
    (if (or (not= (count s1) (count s2))
            (not= (frequencies s1) (frequencies s2)))
      -1
      (let [mismatches (reduce (fn [cnt [a b]]
                                 (if (= a b) cnt (inc cnt)))
                               0
                               (map vector s1 s2))]
        (/ mismatches 2)))))