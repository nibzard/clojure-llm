(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (loop [count-found 0
           candidate 1]
      (if (= count-found n)
        (dec candidate)
        (recur (if (pos? (Long/bitCount (long candidate)))
                 (if (== 1 (Long/bitCount (long candidate)))
                   count-found
                   (inc count-found))
                 count-found)
               (inc candidate))))))