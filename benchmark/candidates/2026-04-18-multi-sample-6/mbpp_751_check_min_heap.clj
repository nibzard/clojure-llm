(defn check_min_heap
  "	Write a function to check if the given vector represents min heap or not. https://www.geeksforgeeks.org/how-to-check-if-a-given-vector-represents-a-binary-heap/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (every?
      (fn [i]
        (let [left  (inc (* 2 i))
              right (+ 2 (* 2 i))
              parent (v i)]
          (and
            (or (>= left n) (<= parent (v left)))
            (or (>= right n) (<= parent (v right))))))
      (range (quot n 2)))))