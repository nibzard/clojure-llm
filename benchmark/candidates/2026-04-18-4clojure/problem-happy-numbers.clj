(defn problem-happy-numbers [a]
  (letfn [(next-num [n]
            (loop [x n
                   s 0]
              (if (zero? x)
                s
                (let [d (mod x 10)]
                  (recur (quot x 10) (+ s (* d d)))))))]
    (loop [n a
           seen #{}]
      (cond
        (= n 1) true
        (seen n) false
        :else (recur (next-num n) (conj seen n))))))