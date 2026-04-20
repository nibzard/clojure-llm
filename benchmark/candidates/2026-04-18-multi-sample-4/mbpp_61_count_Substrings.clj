(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")
        digits (map #(- (int %) (int \0)) s)]
    (->> digits
         (reduce (fn [{:keys [prefix counts total]} d]
                   (let [prefix' (+ prefix (dec d))]
                     {:prefix prefix'
                      :counts (update counts prefix' (fnil inc 0))
                      :total (+ total (get counts prefix' 0))}))
                 {:prefix 0 :counts {0 1} :total 0})
         :total)))