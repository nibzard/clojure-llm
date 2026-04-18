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
  (let [odd-digits? (fn [n]
                      (let [s (str (Math/abs (long n)))
                            first-digit (Character/digit ^char (first s) 10)
                            last-digit (Character/digit ^char (last s) 10)]
                        (and (odd? first-digit)
                             (odd? last-digit))))]
    (count (filter #(and (> % 10) (odd-digits? %)) nums))))