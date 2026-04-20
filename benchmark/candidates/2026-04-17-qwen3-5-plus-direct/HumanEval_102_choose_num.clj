(defn choose_num
  "This function takes two positive numbers x and y and returns the
  biggest even integer number that is in the range [x, y] inclusive. If 
  there's no such number, then the function should return -1.
  For example:
  >>> (choose_num 12 15)
  14
  >>> (choose_num 13 12)
  -1"
  [x y]
  (if (> x y)
    -1
    (let [candidate (if (even? y) y (dec y))]
      (if (>= candidate x)
        candidate
        -1))))