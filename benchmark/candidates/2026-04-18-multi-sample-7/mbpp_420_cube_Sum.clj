(defn cube_Sum
  "	Write a cljthon function to find the cube sum of first n even natural numbers."
  [n]
  (let [n (or n 0)]
    (if (pos? n)
      (reduce + (map #(* % % %) (take n (iterate #(+ % 2) 2))))
      0)))