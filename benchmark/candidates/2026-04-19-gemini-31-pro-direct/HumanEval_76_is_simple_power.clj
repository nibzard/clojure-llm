(defn is_simple_power [x n]
  (cond
    (= x 1) true
    (= n 1) (= x 1)
    (= n 0) (or (= x 0) (= x 1))
    (= n -1) (or (= x 1) (= x -1))
    :else (loop [p 1]
            (let [abs-p (if (< p 0) (- p) p)
                  abs-x (if (< x 0) (- x) x)]
              (cond
                (= p x) true
                (> abs-p abs-x) false
                :else (recur (* p n)))))))