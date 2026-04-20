(defn highest_Power_of_2
  "	Write a cljthon function to find the highest power of 2 that is less than or equal to n."
  [n]
  (when (and n (pos? n))
    (bit-shift-left 1 (dec (Integer/toBinaryString (long n) )))))