(defn special_factorial
  [n]
  (reduce *' (map #(reduce *' (range 1 (inc %))) (range 1 (inc n)))))