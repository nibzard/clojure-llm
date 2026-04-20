(defn check_min_heap
  "	Write a function to check if the given vector represents min heap or not. https://www.geeksforgeeks.org/how-to-check-if-a-given-vector-represents-a-binary-heap/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (every?
     (fn [i]
       (let [parent (v i)
             li (+ (* 2 i) 1)
             ri (+ (* 2 i) 2)]
         (and
          (or (>= li n) (<= parent (v li)))
          (or (>= ri n) (<= parent (v ri))))))
     (range (quot n 2)))))