(defn count_Pairs
  "	Write a cljthon function which takes a list of integers and counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [xs (take n (or arr []))
        total (count xs)
        freqs (vals (frequencies xs))
        total-pairs (/ (* total (dec total)) 2)
        equal-pairs (reduce + 0 (map #(/ (* % (dec %)) 2) freqs))]
    (- total-pairs equal-pairs)))