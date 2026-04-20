(defn find_length
  "Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (empty? string)
    0
    (let [nums (map #(if (= % \0) 1 -1) string)]
      (first
       (reduce (fn [[g-max c-max] n]
                 (let [new-c-max (max n (+ c-max n))]
                   [(max g-max new-c-max) new-c-max]))
               [-1 0]
               nums)))))