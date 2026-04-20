(defn find_Max_Num
  "	Write a cljthon function to find the largest number that can be formed with the given list of digits."
  [arr]
  (if (seq arr)
    (reduce (fn [n d] (+ (* n 10) d)) 0 (sort > arr))
    0))