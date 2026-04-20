(defn is_woodall
  "Write a function to check if the given number is woodball or not."
  [x]
  (let [n (when (number? x) (bigint x))]
    (boolean
     (and n
          (pos? n)
          (loop [k 1N]
            (let [p (bit-shift-left 1 k)
                  w (* p (dec p))]
              (cond
                (= w n) true
                (> w n) false
                :else (recur (inc k))))))))