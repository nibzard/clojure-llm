(defn remove-kth-from-vector
  "Return a new vector with the element at zero-based index k removed.

  If k is out of bounds, return the original vector unchanged.

  Examples:
  (remove-kth-from-vector [1 2 3 4] 2) ;=> [1 2 4]
  (remove-kth-from-vector [:a :b :c] 0) ;=> [:b :c]
  (remove-kth-from-vector [] 0) ;=> []"
  [v k])