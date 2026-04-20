(defn is_woodall
  "Write a function to check if the given number is woodall or not."
  [x]
  (let [n (long x)]
    (and (pos? n)
         (loop [k 1]
           (let [w (* k (dec (bit-shift-left 1 k)))]
             (cond
               (= w n) true
               (> w n) false
               :else (recur (inc k))))))))