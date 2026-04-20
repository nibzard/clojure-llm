(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (let [s (or s "")]
    (loop [idx 0
           cnt 0]
      (let [found (.indexOf ^String s "std" idx)]
        (if (neg? found)
          cnt
          (recur (inc found) (inc cnt)))))))