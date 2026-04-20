(defn count_rotation
  "	Write a cljthon function to count the number of rotations required to generate a sorted vector. https://www.geeksforgeeks.org/count-of-rotations-required-to-generate-a-sorted-vector/"
  [arr]
  (let [v (vec arr)
        n (count v)]
    (cond
      (<= n 1) 0
      :else
      (loop [lo 0
             hi (dec n)]
        (if (> lo hi)
          0
          (if (<= (v lo) (v hi))
            lo
            (let [mid (quot (+ lo hi) 2)
                  next-idx (mod (inc mid) n)
                  prev-idx (mod (+ mid n -1) n)
                  mid-val (v mid)]
              (cond
                (and (<= mid-val (v next-idx))
                     (<= mid-val (v prev-idx)))
                mid

                (<= mid-val (v hi))
                (recur lo (dec mid))

                :else
                (recur (inc mid) hi))))))))