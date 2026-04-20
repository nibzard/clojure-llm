(defn find_length
  "	Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (or (nil? string) (empty? string))
    0
    (let [max-sum (reduce (fn [[curr best] ch]
                            (let [v (if (= ch \0) 1 -1)
                                  curr' (max v (+ curr v))]
                              [curr' (max best curr')]))
                          [0 Long/MIN_VALUE]
                          string)]
      (max 0 (second max-sum)))))