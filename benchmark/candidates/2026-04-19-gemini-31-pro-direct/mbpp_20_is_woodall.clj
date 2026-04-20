(defn is_woodall
  "Write a function to check if the given number is woodall or not."
  [x]
  (if (< x 1)
    false
    (loop [n 1
           pow2 2]
      (let [w (dec (* n pow2))]
        (cond
          (= w x) true
          (> w x) false
          :else (recur (inc n) (* pow2 2)))))))