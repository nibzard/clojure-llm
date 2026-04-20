(defn find_Max_Num
  "	Write a cljthon function to find the largest number that can be formed with the given list of digits."
  [arr]
  (when (seq arr)
    (let [digits (sort > arr)]
      (Long/parseLong (apply str digits)))))