(defn count_Set_Bits
  "	Write a cljthon function to count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (if (nil? n)
    0
    (Long/bitCount (long n))))