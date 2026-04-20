(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (<= n 2) 1
    :else
    (let [q (reduce
              (fn [v i]
                (let [q-prev (nth v (dec i))
                      q-q-prev (nth v q-prev)
                      q-i-q-prev (nth v (- i q-prev))]
                  (conj v (+ q-q-prev q-i-q-prev))))
              [0 1 1]
              (range 3 (inc n)))]
      (nth q n))))