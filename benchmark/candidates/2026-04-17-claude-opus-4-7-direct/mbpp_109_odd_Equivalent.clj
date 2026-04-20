(defn odd_Equivalent
  "Write a cljthon function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (let [len (count s)
        rotations (mod n len)
        shifted-s (str (subs s rotations) (subs s 0 rotations))
        ones (count (filter #(= \1 %) shifted-s))]
    (if (odd? ones) 1 0)))