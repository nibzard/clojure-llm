(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")]
    (loop [chars (seq s)
           idx 0
           prefix-sum 0
           freq {0 1}
           count 0]
      (if-let [ch (first chars)]
        (let [digit (- (int ch) (int \0))
              new-prefix-sum (+ prefix-sum digit)
              key (- new-prefix-sum (inc idx))
              matches (get freq key 0)]
          (recur (next chars)
                 (inc idx)
                 new-prefix-sum
                 (update freq key (fnil inc 0))
                 (+ count matches)))
        count))))