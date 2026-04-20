(defn check_Consecutive
  "	Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (let [s (seq l)]
    (cond
      (nil? s) false
      (= 1 (count s)) true
      :else (let [sorted (sort s)]
              (= sorted
                 (range (first sorted) (inc (last sorted))))))))