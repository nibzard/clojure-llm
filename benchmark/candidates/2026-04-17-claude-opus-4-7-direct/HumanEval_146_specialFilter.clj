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
  (let [odd-digits #{1 3 5 7 9}]
    (count
      (filter
        (fn [n]
          (and (> n 10)
               (let [s (str n)
                     first-digit (Character/digit (first s) 10)
                     last-digit (Character/digit (last s) 10)]
                 (and (contains? odd-digits first-digit)
                      (contains? odd-digits last-digit)))))
        nums))))