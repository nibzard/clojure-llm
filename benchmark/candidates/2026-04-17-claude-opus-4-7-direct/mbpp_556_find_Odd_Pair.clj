(defn find_Odd_Pair
  "Write a cljthon function to count the number of pairs whose xor value is odd."
  [A N]
  (let [even-count (count (filter even? A))
        odd-count  (- N even-count)]
    (* even-count odd-count)))