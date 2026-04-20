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
                (let [qi-1 (nth v (dec i))
                      qi-qi-1 (nth v qi-1)]
                  (conj v (+ qi-qi-1 (nth v (- i qi-1))))))
              [0 1 1]
              (range 3 (inc n)))]
      (nth q n))))