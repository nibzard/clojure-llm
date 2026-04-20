(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [freqs (frequencies (or arr []))]
    (reduce
      (fn [count [x fx]]
        (let [y (- sum x)]
          (cond
            (< x y) (+ count (* fx (get freqs y 0)))
            (= x y) (+ count (quot (* fx (dec fx)) 2))
            :else count)))
      0
      freqs)))