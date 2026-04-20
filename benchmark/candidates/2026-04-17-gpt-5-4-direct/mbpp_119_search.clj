(defn search
  "	Write a cljthon function to find the element that appears only once in a sorted vector."
  [arr]
  (when (seq arr)
    (loop [lo 0
           hi (dec (count arr))]
      (if (= lo hi)
        (nth arr lo)
        (let [mid (+ lo (quot (- hi lo) 2))
              mid (if (odd? mid) (dec mid) mid)]
          (if (= (nth arr mid) (nth arr (inc mid)))
            (recur (+ mid 2) hi)
            (recur lo mid)))))))