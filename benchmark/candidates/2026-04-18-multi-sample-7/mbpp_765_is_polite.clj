(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (loop [count-found 0
           x 1]
      (if (= count-found n)
        (dec x)
        (recur (if (pos? (bit-and x (dec x)))
                 (inc count-found)
                 count-found)
               (inc x))))))