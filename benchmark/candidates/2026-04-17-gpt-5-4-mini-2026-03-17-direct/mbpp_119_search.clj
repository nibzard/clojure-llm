(defn search
  "Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (let [n (count arr)]
    (cond
      (zero? n) nil
      (= 1 n) (first arr)
      :else
      (loop [lo 0
             hi (dec n)]
        (if (= lo hi)
          (nth arr lo)
          (let [mid (quot (+ lo hi) 2)
                mid-even? (even? mid)]
            (cond
              (and (= mid 0) (not= (nth arr mid) (nth arr (inc mid))))
              (nth arr mid)

              (and (= mid (dec n)) (not= (nth arr mid) (nth arr (dec mid))))
              (nth arr mid)

              (= (nth arr mid) (nth arr (dec mid)))
              (if mid-even?
                (recur (+ mid 1) hi)
                (recur lo (- mid 2)))

              (= (nth arr mid) (nth arr (inc mid)))
              (if mid-even?
                (recur lo (- mid 1))
                (recur (+ mid 2) hi))

              :else
              (nth arr mid))))))))