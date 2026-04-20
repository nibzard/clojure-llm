(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [freqs (frequencies (or arr []))]
    (reduce
      (fn [cnt [x fx]]
        (let [y (- sum x)
              fy (get freqs y 0)]
          (cond
            (< x y) (+ cnt (* fx fy))
            (= x y) (+ cnt (quot (* fx (dec fx)) 2))
            :else cnt)))
      0
      freqs)))