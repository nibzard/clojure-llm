(defn find_Average_Of_Cube
  "	Write a cljthon function to find the average of cubes of first n natural numbers."
  [n]
  (when (pos? n)
    (/ (reduce + (map #(Math/pow % 3) (range 1 (inc n))))
       n)))