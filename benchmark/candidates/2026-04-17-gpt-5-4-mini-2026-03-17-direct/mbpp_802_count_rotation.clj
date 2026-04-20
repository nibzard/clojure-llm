(defn count_rotation
  "	Write a cljthon function to count the number of rotations required to generate a sorted vector. https://www.geeksforgeeks.org/count-of-rotations-required-to-generate-a-sorted-vector/"
  [arr]
  (let [v (vec arr)
        n (count v)]
    (cond
      (zero? n) 0
      (= n 1) 0
      :else
      (loop [lo 0
             hi (dec n)]
        (if (< lo hi)
          (let [mid (quot (+ lo hi) 2)]
            (if (> (v mid) (v hi))
              (recur (inc mid) hi)
              (recur lo mid)))
          lo)))))