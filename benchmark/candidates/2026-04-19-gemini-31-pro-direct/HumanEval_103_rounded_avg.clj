(defn rounded_avg [n m]
  (if (> n m)
    -1
    (let [avg (/ (+ n m) 2.0)
          rounded (long (Math/rint avg))]
      (str "0b" (Long/toBinaryString rounded)))))