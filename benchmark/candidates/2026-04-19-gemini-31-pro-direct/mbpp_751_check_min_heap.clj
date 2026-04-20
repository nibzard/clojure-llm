(defn check_min_heap
  "Write a function to check if the given vector represents min heap or not."
  [arr]
  (let [n (count arr)]
    (every? (fn [i]
              (let [val (nth arr i)
                    left (+ (* 2 i) 1)
                    right (+ (* 2 i) 2)]
                (and (or (>= left n) (<= val (nth arr left)))
                     (or (>= right n) (<= val (nth arr right))))))
            (range (quot n 2)))))