(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (let [n (cond
            (integer? x) x
            (and (number? x) (== x (long x))) (long x)
            :else nil)]
    (boolean
     (when (and n (pos? n))
       (loop [k 1]
         (let [v (* k (bit-shift-left 1 k))]
           (cond
             (= v n) true
             (> v n) false
             :else (recur (inc k)))))))))