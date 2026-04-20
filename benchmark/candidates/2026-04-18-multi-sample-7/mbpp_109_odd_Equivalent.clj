(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        len (count s)
        n (max 0 (long (or n 0)))]
    (cond
      (zero? len) 0
      (= len 1) (if (= \1 (first s)) n 0)
      :else
      (let [ones (count (filter #(= % \1) s))]
        (if (zero? ones)
          0
          (* (quot n len) ones)
          (+ (* (quot n len) ones)
             (count (filter #(= % \1) (take (mod n len) s)))))))))