(defn find_length
  "	Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (let [s (or string "")]
    (if (empty? s)
      0
      (let [max-sum (reduce (fn [[current best] ch]
                              (let [v (if (= ch \0) 1 -1)
                                    current' (max v (+ current v))
                                    best' (max best current')]
                                [current' best']))
                            [0 Integer/MIN_VALUE]
                            s)]
        (max 0 (second max-sum))))))