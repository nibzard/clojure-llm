(defn check_Consecutive
  "	Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (let [s (seq l)]
    (if (or (nil? s) (= 1 (count s)))
      true
      (let [sorted (sort s)]
        (every? true?
                (map (fn [[a b]] (= 1 (- b a)))
                     (partition 2 1 sorted)))))))