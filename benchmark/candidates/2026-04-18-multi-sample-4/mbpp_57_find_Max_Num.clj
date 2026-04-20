(defn find_Max_Num
  "Write a cljthon function to find the largest number that can be formed with the given list of digits."
  [arr]
  (let [digits (seq arr)]
    (if digits
      (Long/parseLong (apply str (sort > digits)))
      0)))