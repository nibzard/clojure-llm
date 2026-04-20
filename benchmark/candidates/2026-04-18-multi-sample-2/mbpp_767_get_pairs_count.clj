(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [{:keys [count-map pairs]}
        (reduce (fn [{:keys [count-map pairs]} x]
                  (let [needed (- sum x)
                        matches (get count-map needed 0)]
                    {:count-map (update count-map x (fnil inc 0))
                     :pairs (+ pairs matches)}))
                {:count-map {} :pairs 0}
                (or arr []))]
    pairs))