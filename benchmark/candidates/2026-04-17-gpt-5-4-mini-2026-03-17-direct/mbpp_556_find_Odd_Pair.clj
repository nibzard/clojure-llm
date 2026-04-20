(defn find_Odd_Pair
  "	Write a cljthon function to count the number of pairs whose xor value is odd."
  [A N]
  (let [odd-count (count (filter odd? (take N A)))
        even-count (- N odd-count)]
    (* odd-count even-count)))