(defn cube-Sum
  "Write a cljthon function to find the cube sum of first n even natural numbers."
  [n]
  (if (or (nil? n) (<= n 0))
    0
    (let [even-nums (map #(* 2 %) (range 1 (inc n)))]
      (reduce + (map #(* % % %) even-nums)))))