(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (let [n (when (integer? x) x)]
    (boolean
     (when (and n (pos? n))
       (loop [k 1
              p 2]
         (cond
           (> p n) false
           (= (* k p) n) true
           :else (recur (inc k) (* 2 p))))))))