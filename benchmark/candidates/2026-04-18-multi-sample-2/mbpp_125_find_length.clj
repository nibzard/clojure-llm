(defn find_length
  "	Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (or (nil? string) (empty? string))
    0
    (let [step (fn [[curr best] ch]
                 (let [v (if (= ch \0) 1 -1)
                       curr' (max v (+ curr v))
                       best' (max best curr')]
                   [curr' best']))]
      (second (reduce step [0 Integer/MIN_VALUE] string)))))