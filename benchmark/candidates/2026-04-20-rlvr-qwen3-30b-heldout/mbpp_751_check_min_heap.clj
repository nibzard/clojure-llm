(defn check_min_heap
  "	Write a function to check if the given vector represents min heap or not. https://www.geeksforgeeks.org/how-to-check-if-a-given-vector-represents-a-binary-heap/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (boolean
     (every?
      (fn [i]
        (let [left (* 2 i)
              right (inc left)]
          (and (>= left n)
               (>= right n)
               (<= (v i) (v left))
               (<= (v i) (v right)))))
      (range 0 (quot n 2))))))