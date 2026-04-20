(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (let [n (when (integer? x) x)]
    (boolean
     (when (and n (pos? n) (odd? n))
       (loop [k 1
              p 2]
         (cond
           (= (* k p) n) true
           (> (* k p) n) false
           :else (recur (inc k) (* 2 p))))))))