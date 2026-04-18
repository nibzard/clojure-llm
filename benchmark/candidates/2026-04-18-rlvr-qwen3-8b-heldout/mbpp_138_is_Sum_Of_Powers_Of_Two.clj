(defn is_Sum_Of_Powers_Of_Two
  "	Write a cljthon function to check whether the given number can be represented as sum of non-zero powers of 2 or not."
  [n]
  (let [n (when (number? n) (long n))]
    (and (integer? n)
         (not (neg? n))
         (let [bits (bit-count n)]
           (or (zero? n)
               (= bits 1))))))