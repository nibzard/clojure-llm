(defn find_Average_Of_Cube
  "	Write a cljthon function to find the average of cubes of first n natural numbers."
  [n]
  (if (and (number? n) (pos? n))
    (/ (* n (inc n) (inc n)) 4)
    0))