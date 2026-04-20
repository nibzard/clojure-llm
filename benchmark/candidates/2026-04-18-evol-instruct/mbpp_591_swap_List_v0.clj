(defn swap-vec-ends
  "Return a new vector with the first and last elements swapped.

  Examples:
  (swap-vec-ends [1 2 3 4]) => [4 2 3 1]
  (swap-vec-ends [:a :b]) => [:b :a]
  (swap-vec-ends [42]) => [42]
  (swap-vec-ends []) => []"
  [v]
  (cond
    (nil? v) nil
    (<= (count v) 1) v
    :else (assoc v
                 0 (peek v)
                 (dec (count v)) (first v))))