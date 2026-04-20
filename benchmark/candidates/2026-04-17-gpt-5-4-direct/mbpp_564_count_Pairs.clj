(defn count_Pairs
  "Write a cljthon function which takes a list of integers and counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [xs (take n (or arr []))
        total (count xs)
        freqs (frequencies xs)
        total-pairs (/ (* total (dec total)) 2)
        equal-pairs (reduce + 0 (map (fn [c] (/ (* c (dec c)) 2)) (vals freqs)))]
    (- total-pairs equal-pairs)))