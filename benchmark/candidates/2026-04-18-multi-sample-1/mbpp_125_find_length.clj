(defn find_length
  "	Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (or (nil? string) (empty? string))
    0
    (let [step (fn [[max-ending-here max-so-far] ch]
                 (let [v (if (= ch \0) 1 -1)
                       meh (max v (+ max-ending-here v))
                       msf (max max-so-far meh)]
                   [meh msf]))
          [_ result] (reduce step [0 Integer/MIN_VALUE] string)]
      (max 0 result))))