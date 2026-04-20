(defn check_min_heap
  "	Write a function to check if the given vector represents min heap or not. https://www.geeksforgeeks.org/how-to-check-if-a-given-vector-represents-a-binary-heap/"
  [arr]
  (let [n (count arr)]
    (every? (fn [idx]
              (let [left (+ (* 2 idx) 1)
                    right (+ (* 2 idx) 2)]
                (and (or (>= left n)
                         (<= (nth arr idx) (nth arr left)))
                     (or (>= right n)
                         (<= (nth arr idx) (nth arr right))))))
            (range (quot n 2)))))