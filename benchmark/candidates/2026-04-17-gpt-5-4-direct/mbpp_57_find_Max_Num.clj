(defn find_Max_Num
  "	Write a cljthon function to find the largest number that can be formed with the given list of digits."
  [arr]
  (let [digits (->> arr
                    (remove nil?)
                    (map #(cond
                            (number? %) (long %)
                            (char? %) (Character/digit ^char % 10)
                            (string? %) (try
                                          (Long/parseLong %)
                                          (catch Exception _ nil))
                            :else nil))
                    (filter #(and (some? %) (<= 0 % 9)))
                    (sort >))]
    (if (seq digits)
      (reduce (fn [n d] (+ (* n 10) d)) 0 digits)
      0)))