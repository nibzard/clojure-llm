(defn is_Diff
  "	Write a cljthon function to find whether a number is divisible by 11."
  [n]
  (and (integer? n)
       (zero? (mod n 11))))