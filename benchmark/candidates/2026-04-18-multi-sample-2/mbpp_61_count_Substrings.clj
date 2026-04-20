(defn count_Substrings
  "Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [s (or s "")
        n (count s)
        digits (mapv #(- (int %) (int \0)) s)]
    (loop [i 0
           prefix 0
           freq {0 1}
           total 0]
      (if (= i n)
        total
        (let [prefix' (+ prefix (- (digits i) 1))
              seen (get freq prefix' 0)]
          (recur (inc i)
                 prefix'
                 (update freq prefix' (fnil inc 0))
                 (+ total seen)))))))