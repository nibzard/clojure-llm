(defn count_rotation
  "	Write a cljthon function to count the number of rotations required to generate a sorted vector. https://www.geeksforgeeks.org/count-of-rotations-required-to-generate-a-sorted-vector/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (cond
      (<= n 1) 0
      :else
      (loop [lo 0
             hi (dec n)]
        (if (<= lo hi)
          (let [mid (+ lo (quot (- hi lo) 2))
                prev (mod (+ mid n -1) n)
                next (mod (inc mid) n)]
            (cond
              (<= (v lo) (v hi)) lo
              (and (<= (v mid) (v prev))
                   (<= (v mid) (v next))) mid
              (<= (v mid) (v hi)) (recur lo (dec mid))
              :else (recur (inc mid) hi)))
          0)))))