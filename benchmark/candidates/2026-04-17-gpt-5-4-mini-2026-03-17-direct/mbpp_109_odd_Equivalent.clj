(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        n (long (or n 0))
        len (count s)]
    (if (zero? len)
      0
      (let [ones (count (filter #{\1} s))
            zeros (- len ones)
            odd-count (if (odd? len)
                        ones
                        zeros)
            rotations (min n len)]
        (if (zero? rotations)
          0
          (* rotations odd-count))))))