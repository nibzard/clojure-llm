(defn insert-element-into-vector
  "Return a new vector with `element` inserted before each item in `v`.

  Examples:
  (insert-element-into-vector [1 2 3] :x)
  ;; => [:x 1 :x 2 :x 3]

  (insert-element-into-vector [] :x)
  ;; => []"
  [v element]
  (vec (mapcat (fn [x] [element x]) v)))