(defn find_Average_Of_Cube
  "	Write a cljthon function to find the average of cubes of first n natural numbers."
  [n]
  (if (pos? n)
    (/ (reduce + (map #(* % % %) (range 1 (inc n)))) n)
    0))