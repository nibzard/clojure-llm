(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (let [n (when (integer? x) x)]
    (boolean
     (and n
          (pos? n)
          (loop [k 1]
            (let [pow (bit-shift-left 1 k)
                  woodall (* (dec pow) pow)]
              (cond
                (= woodall n) true
                (> woodall n) false
                :else (recur (inc k)))))))))