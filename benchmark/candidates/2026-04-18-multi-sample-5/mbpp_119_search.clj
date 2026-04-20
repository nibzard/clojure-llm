(defn search
  "	Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (when (seq arr)
    (let [n (count arr)]
      (loop [lo 0
             hi (dec n)]
        (if (> lo hi)
          nil
          (let [mid (+ lo (quot (- hi lo) 2))
                even-mid (if (odd? mid) (dec mid) mid)]
            (cond
              (= lo hi) (nth arr lo)
              (= even-mid (dec n)) (nth arr even-mid)
              (= (nth arr even-mid) (nth arr (inc even-mid)))
              (recur (+ even-mid 2) hi)
              :else
              (recur lo even-mid))))))))