(defn catalan_number
  "Write a function which returns nth catalan number."
  [num]
  (let [n (long num)]
    (cond
      (neg? n) nil
      (zero? n) 1N
      :else
      (loop [i 1
             c 1N]
        (if (> i n)
          c
          (recur (inc i)
                 (/ (*' c (+ n i)) (inc i))))))))