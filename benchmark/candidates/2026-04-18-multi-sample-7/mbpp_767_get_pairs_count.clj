(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (if (seq arr)
    (second
     (reduce (fn [[freqs count] x]
               (let [needed (- sum x)
                     matches (get freqs needed 0)]
                 [(update freqs x (fnil inc 0))
                  (+ count matches)]))
             [{} 0]
             arr))
    0))