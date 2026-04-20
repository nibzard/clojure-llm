(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (let [s (or s "")]
    (loop [idx 0
           cnt 0]
      (let [pos (.indexOf ^String s "std" idx)]
        (if (neg? pos)
          cnt
          (recur (inc pos) (inc cnt)))))))