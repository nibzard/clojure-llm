(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        len (count s)
        n (max 0 (long (or n 0)))]
    (cond
      (zero? len) 0
      (zero? n) 0
      :else
      (let [ones (count (filter #(= % \1) s))]
        (if (zero? ones)
          0
          (let [full-cycles (quot n len)
                remainder (mod n len)
                doubled (str s s)
                extra (count
                       (for [i (range remainder)
                             :when (= (nth doubled (dec (+ len i))) \1)]
                         1))]
            (+ (* full-cycles ones) extra))))))