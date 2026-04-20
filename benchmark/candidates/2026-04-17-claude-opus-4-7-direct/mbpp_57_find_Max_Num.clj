(defn find-Max-Num
  "Write a cljthon function to find the largest number that can be formed with the given list of digits."
  [arr]
  (when (seq arr)
    (->> arr
         (sort >)
         (apply str)
         (bigdec))))