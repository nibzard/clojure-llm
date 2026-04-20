(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (= n 1) 1
    (= n 2) 1
    :else
    (let [q (reduce
              (fn [v i]
                (let [qi-1 (v (dec i))
                      q-qi-1 (v qi-1)
                      q-i-qi-1 (v (- i qi-1))]
                  (assoc v i (+ q-qi-1 q-i-qi-1))))
              [0 1 1]
              (range 3 (inc n)))]
      (q n))))