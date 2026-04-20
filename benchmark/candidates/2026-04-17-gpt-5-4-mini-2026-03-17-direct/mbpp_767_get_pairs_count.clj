(defn get_pairs_count
  "Write a cljthon function to count the number of pairs whose sum is equal to `sum`."
  [arr sum]
  (let [freqs (frequencies arr)]
    (reduce
      (fn [count x]
        (let [y (- sum x)]
          (cond
            (< y x) count
            (= y x) (+ count (quot (get freqs x 0) 2))
            :else (+ count (* (get freqs x 0) (get freqs y 0))))))
      0
      (keys freqs))))