(defn find_First_Missing
  "	Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (loop [expected 0
         xs (seq array)]
    (if-let [x (first xs)]
      (cond
        (< x expected) (recur expected (next xs))
        (= x expected) (recur (inc expected) (next xs))
        :else expected)
      expected)))