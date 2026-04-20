(defn search
  "	Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (when (seq arr)
    (let [v (vec arr)
          n (count v)]
      (loop [lo 0
             hi (dec n)]
        (if (= lo hi)
          (v lo)
          (let [mid (bit-and (+ lo hi) -2)]
            (if (= (v mid) (v (inc mid)))
              (recur (+ mid 2) hi)
              (recur lo mid))))))))