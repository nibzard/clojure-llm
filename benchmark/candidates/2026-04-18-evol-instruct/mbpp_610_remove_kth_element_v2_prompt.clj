(defn remove-kth-assoc
  "Return a new vector with the k-th element removed from v.

  Uses zero-based indexing and supports nil input by returning an empty vector.

  Examples:
  (remove-kth-assoc [1 2 3 4] 2) => [1 2 4]
  (remove-kth-assoc [:a :b :c] 0) => [:b :c]
  (remove-kth-assoc nil 1) => []"
  [v k])