(defn get_pairs_count
  "Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [freqs (frequencies (or arr []))]
    (/ (reduce-kv
         (fn [acc x cnt]
           (+ acc (* cnt (get freqs (- sum x) 0))))
         0
         freqs)
       2)))