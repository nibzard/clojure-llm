(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (cond
            (nil? num) 0
            (number? num) (long num)
            :else (long (bigint num)))]
    (if (neg? n)
      nil
      (nth
       (map first
            (iterate
             (fn [[c k]]
               [(/ (* c 2N (inc (* 2N k))) (+ k 2N))
                (inc k)])
             [1N 0N]))
       n))))