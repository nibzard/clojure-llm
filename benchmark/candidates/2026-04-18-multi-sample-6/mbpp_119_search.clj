(defn search
  "	Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (when (seq arr)
    (let [n (count arr)]
      (loop [lo 0
             hi (dec n)]
        (if (= lo hi)
          (nth arr lo)
          (let [mid (bit-and (+ lo hi) -2)
                a   (nth arr mid)
                b   (nth arr (inc mid))]
            (if (= a b)
              (recur (+ mid 2) hi)
              (recur lo mid))))))))