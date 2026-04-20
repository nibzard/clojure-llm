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
  (letfn [(odd-digit? [n]
            (#{1 3 5 7 9} n))
          (first-digit [n]
            (loop [n (Math/abs (long n))]
              (if (< n 10)
                n
                (recur (quot n 10)))))
          (last-digit [n]
            (mod (Math/abs (long n)) 10))]
    (count (filter (fn [x]
                     (and (number? x)
                          (> x 10)
                          (odd-digit? (first-digit x))
                          (odd-digit? (last-digit x))))
                   nums))))