(defn is_Sum_Of_Powers_Of_Two
  "	Write a cljthon function to check whether the given number can be represented as sum of non-zero powers of 2 or not."
  [n]
  (let [x (long (Math/abs (long n)))]
    (cond
      (nil? x) false
      (zero? x) false
      (integer? n) (neg? (bit-and x (dec x)))
      :else false)))