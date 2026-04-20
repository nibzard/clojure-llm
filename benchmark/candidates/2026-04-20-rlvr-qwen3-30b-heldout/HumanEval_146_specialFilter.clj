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
  (letfn [(odd-digits? [n]
            (let [s (str (Math/abs (long n)))]
              (and (odd? (Character/digit ^char (first s) 10))
                   (odd? (Character/digit ^char (last s) 10)))))]
    (count (filter (fn [x] (and (number? x) (> x 10) (odd-digits? x))) nums))))