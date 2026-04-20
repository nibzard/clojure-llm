(defn find_Odd_Pair
  "	Write a cljthon function to count the number of pairs whose xor value is odd."
  [A N]
  (let [xs (take (max 0 (or N 0)) (or A []))
        odd-count (count (filter odd? xs))
        even-count (- (count xs) odd-count)]
    (* odd-count even-count)))