(defn count_rotation
  "	Write a cljthon function to count the number of rotations required to generate a sorted vector. https://www.geeksforgeeks.org/count-of-rotations-required-to-generate-a-sorted-vector/"
  [arr]
  (let [v (vec arr)
        n (count v)]
    (cond
      (<= n 1) 0
      :else
      (loop [low 0
             high (dec n)]
        (if (> low high)
          0
          (let [mid (+ low (quot (- high low) 2))
                prev (mod (+ mid n -1) n)
                next (mod (inc mid) n)
                mid-val (v mid)
                low-val (v low)
                high-val (v high)]
            (cond
              (<= low-val high-val) low
              (and (<= mid-val (v prev))
                   (<= mid-val (v next))) mid
              (<= mid-val high-val) (recur low (dec mid))
              :else (recur (inc mid) high)))))))