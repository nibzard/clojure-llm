(defn cube_Sum
  "	Write a cljthon function to find the cube sum of first n even natural numbers."
  [n]
  (reduce + (map #(Math/pow % 3) (take n (iterate #(+ % 2) 2)))))