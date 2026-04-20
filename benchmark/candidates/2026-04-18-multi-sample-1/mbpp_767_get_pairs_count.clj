(defn get_pairs_count
  "	Write a cljthon function to count the number of pairs whose sum is equal to ‘sum’. The funtion gets as input a list of numbers and the sum,"
  [arr sum]
  (let [freqs (frequencies (or arr []))]
    (/ (reduce-kv
         (fn [acc x cnt]
           (let [y (- sum x)]
             (cond
               (< x y) (+ acc (* cnt (get freqs y 0)))
               (= x y) (+ acc (/ (* cnt (dec cnt)) 2))
               :else acc)))
         0
         freqs)
       1)))