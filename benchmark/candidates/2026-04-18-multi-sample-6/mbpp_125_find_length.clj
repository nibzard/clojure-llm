(defn find_length
  "	Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (or (nil? string) (empty? string))
    0
    (let [[max-so-far _]
          (reduce
           (fn [[best curr] ch]
             (let [v (if (= ch \0) 1 -1)
                   curr' (max v (+ curr v))
                   best' (max best curr')]
               [best' curr']))
           [Long/MIN_VALUE 0]
           string)]
      (max 0 max-so-far))))