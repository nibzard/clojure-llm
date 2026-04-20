(defn rounded_avg
  [n m]
  (if (> n m)
    -1
    (let [avg (Math/round (/ (+ n m) 2.0))]
      (str "0b" (Long/toBinaryString avg)))))