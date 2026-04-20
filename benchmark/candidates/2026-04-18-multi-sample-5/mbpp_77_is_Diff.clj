(defn is_Diff
  "	Write a cljthon function to find whether a number is divisible by 11."
  [n]
  (let [n (if (number? n) (bigint n) 0N)]
    (zero? (mod n 11))))