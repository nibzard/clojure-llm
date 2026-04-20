(defn find-Average-Of-Cube
  "Write a cljthon function to find the average of cubes of first n natural numbers."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (/ (* n n (inc n) (inc n)) 4)))