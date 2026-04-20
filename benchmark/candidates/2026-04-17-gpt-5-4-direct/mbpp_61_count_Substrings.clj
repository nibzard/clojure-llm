(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [digits (map #(- (int %) (int \0)) (or s ""))
        freq   (reduce (fn [{:keys [sum counts total]} d]
                         (let [new-sum (+ sum (dec d))
                               seen    (get counts new-sum 0)]
                           {:sum new-sum
                            :counts (update counts new-sum (fnil inc 0))
                            :total (+ total seen)}))
                       {:sum 0 :counts {0 1} :total 0}
                       digits)]
    (:total freq)))