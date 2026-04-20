(defn specialFilter
  "Write a function that takes a vector of numbers as input and returns 
  the number of elements in the vector that are greater than 10 and both 
  first and last digits of a number are odd (1, 3, 5, 7, 9).
  For example:
  >>> (specialFilter [15 -73 14 -15])
  1
  >>> (specialFilter [33 -2 -3 45 21 109])
  2"
  [nums]
  (let [odd-digit? #{\1 \3 \5 \7 \9}
        special? (fn [n]
                   (let [s (str n)
                         digits (seq (re-seq #"\d" s))]
                     (and (> n 10)
                          digits
                          (odd-digit? (first digits))
                          (odd-digit? (last digits)))))]
    (count (filter special? nums))))