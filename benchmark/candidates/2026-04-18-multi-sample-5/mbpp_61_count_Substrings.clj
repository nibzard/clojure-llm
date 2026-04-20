(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")
        digits (map #(- (int %) (int \0)) s)
        freqs (reduce (fn [{:keys [sum idx counts total]} d]
                        (let [sum' (+ sum d)
                              idx' (inc idx)
                              key (- sum' idx')
                              matches (get counts key 0)]
                          {:sum sum'
                           :idx idx'
                           :counts (update counts key (fnil inc 0))
                           :total (+ total matches)}))
                      {:sum 0 :idx 0 :counts {0 1} :total 0}
                      digits)]
    (:total freqs)))