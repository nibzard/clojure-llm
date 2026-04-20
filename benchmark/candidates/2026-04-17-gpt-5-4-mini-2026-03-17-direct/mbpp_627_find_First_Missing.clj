(defn find_First_Missing
  "	Write a cljthon function to find the smallest missing number from a sorted list of natural numbers."
  [array]
  (let [arr (vec array)
        n (count arr)]
    (loop [low 0
           high n]
      (if (> low high)
        low
        (let [mid (quot (+ low high) 2)]
          (if (= (nth arr mid nil) mid)
            (recur (inc mid) high)
            (recur low (dec mid))))))))