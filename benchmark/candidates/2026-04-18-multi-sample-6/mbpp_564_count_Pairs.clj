(defn count_Pairs
  "	Write a cljthon function which takes a list of integers and counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [xs (take n (or arr []))
        total (count xs)
        freqs (frequencies xs)
        equal-pairs (reduce + 0 (map (fn [f] (quot (* f (dec f)) 2)) (vals freqs)))
        all-pairs (quot (* total (dec total)) 2)]
    (- all-pairs equal-pairs)))