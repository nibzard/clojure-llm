(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [[_ count]
        (reduce (fn [[freqs cnt] x]
                  (let [needed (- sum x)
                        matches (get freqs needed 0)]
                    [(update freqs x (fnil inc 0))
                     (+ cnt matches)]))
                [{} 0]
                (or arr []))]
    count))