(defn check_min_heap
  "	Write a function to check if the given vector represents min heap or not. https://www.geeksforgeeks.org/how-to-check-if-a-given-vector-represents-a-binary-heap/"
  [arr]
  (let [n (count arr)]
    (or (<= n 1)
        (every?
         (fn [i]
           (let [left  (inc (* 2 i))
                 right (+ 2 (* 2 i))
                 v     (nth arr i)]
             (and
              (or (>= left n) (<= v (nth arr left)))
              (or (>= right n) (<= v (nth arr right))))))
         (range (quot n 2))))))