(defn count_Substrings
  "	Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")
        digits (mapv #(- (int %) (int \0)) s)
        prefix (vec (reductions + 0 (map #(- % 1) digits)))
        n (count digits)]
    (loop [i 0
           freqs {0 1}
           total 0]
      (if (= i n)
        total
        (let [v (nth prefix (inc i))
              seen (get freqs v 0)]
          (recur (inc i)
                 (assoc freqs v (inc seen))
                 (+ total seen)))))))