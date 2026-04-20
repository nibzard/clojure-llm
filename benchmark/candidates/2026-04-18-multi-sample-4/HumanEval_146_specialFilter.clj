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
  (letfn [(odd-digit? [d] (contains? #{1 3 5 7 9} d))
          (first-digit [n]
            (loop [x (Math/abs (long n))]
              (if (< x 10)
                x
                (recur (quot x 10)))))
          (last-digit [n]
            (mod (Math/abs (long n)) 10))]
    (count
     (filter #(and (> % 10)
                   (odd-digit? (first-digit %))
                   (odd-digit? (last-digit %)))
             nums))))