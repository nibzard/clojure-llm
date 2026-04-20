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
          (if (<= (v low) (v high))
            low
            (let [mid (quot (+ low high) 2)
                  next-idx (mod (inc mid) n)
                  prev-idx (mod (+ mid n -1) n)]
              (cond
                (and (<= (v mid) (v next-idx))
                     (<= (v mid) (v prev-idx)))
                mid

                (<= (v low) (v mid))
                (recur (inc mid) high)

                :else
                (recur low (dec mid)))))))))