(defn check_Consecutive
  "	Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (let [xs (seq l)]
    (cond
      (nil? xs) true
      (= 1 (count xs)) true
      :else
      (let [sorted-xs (sort xs)]
        (every? true?
                (map (fn [[a b]] (= b (inc a)))
                     (partition 2 1 sorted-xs)))))))