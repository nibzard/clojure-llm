(defn is_Even
  "	Write a cljthon function to check whether the given number is even or not."
  [n]
  (and (number? n) (zero? (mod n 2))))