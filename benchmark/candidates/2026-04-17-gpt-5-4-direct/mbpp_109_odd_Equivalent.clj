(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        len (count s)]
    (cond
      (or (zero? len) (nil? n) (<= n 0)) 0
      :else
      (let [ones (count (filter #(= % \1) s))
            full-cycles (quot n len)
            remainder (mod n len)
            doubled (str s s)
            extra (count
                   (for [i (range remainder)
                         :when (= \1 (.charAt doubled (+ i (dec len))))]
                     1))]
        (+ (* full-cycles ones) extra)))))