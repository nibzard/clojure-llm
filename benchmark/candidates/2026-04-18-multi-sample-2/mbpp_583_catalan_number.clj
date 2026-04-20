(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (cond
            (nil? num) 0
            (number? num) (long num)
            :else (long (bigint num)))]
    (if (neg? n)
      nil
      (let [n (bigint n)]
        (/ (reduce *' 1N (range (+ n 2N) (*' 2N n) 1N))
           (reduce *' 1N (range 2N (+ n 1N) 1N)))))))