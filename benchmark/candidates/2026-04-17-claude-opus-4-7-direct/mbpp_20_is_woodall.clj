(defn is_woodall
  "Write a function to check if the given number is woodball or not."
  [x]
  (and (integer? x)
       (pos? x)
       (loop [n 1]
         (let [woodall (* n (dec (bit-shift-left 1 n)))]
           (cond
             (= woodall x) true
             (> woodall x) false
             :else (recur (inc n)))))))