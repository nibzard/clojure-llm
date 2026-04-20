(defn odd_Equivalent
  "	Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [s (or s "")
        len (count s)
        ones (count (filter #(= % \1) s))
        rotations (cond
                    (nil? n) 0
                    (neg? n) 0
                    :else n)]
    (cond
      (zero? len) 0
      (zero? rotations) (if (= (last s) \1) 1 0)
      (>= rotations len) ones
      :else (count (filter #(= % \1) (take rotations (reverse s)))))))