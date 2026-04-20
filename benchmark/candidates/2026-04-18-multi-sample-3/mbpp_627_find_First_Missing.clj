(defn find_First_Missing
  "	Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (let [arr (or array [])]
    (loop [xs (seq arr)
           expected 0]
      (if-let [s xs]
        (let [x (first s)]
          (cond
            (< x expected) (recur (next s) expected)
            (= x expected) (recur (next s) (inc expected))
            :else expected))
        expected))))