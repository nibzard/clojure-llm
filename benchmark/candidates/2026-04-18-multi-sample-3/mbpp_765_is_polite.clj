(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (loop [count 0
           x 1]
      (if (= count n)
        (dec x)
        (recur (if (zero? (bit-and x (dec x))) count (inc count))
               (inc x))))))