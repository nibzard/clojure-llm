(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (letfn [(power-of-two? [x]
              (and (pos? x)
                   (zero? (bit-and x (dec x)))))]
      (nth (remove power-of-two? (iterate inc 1)) (dec n)))))