(defn is_strictly_increasing
  "You are given a collection of numbers `xs`.

  Return true if:
  - the collection has at least 2 elements
  - every adjacent pair is strictly increasing
  - nil values are ignored

  Examples:
  >>> (is_strictly_increasing [1 2 3])
  true
  >>> (is_strictly_increasing [1 1 2])
  false
  >>> (is_strictly_increasing [nil 2 5 9])
  true
  >>> (is_strictly_increasing [3 nil 2])
  false
  >>> (is_strictly_increasing [])
  false"
  [xs]
  (let [ys (remove nil? xs)]
    (and (>= (count ys) 2)
         (every? true? (map < ys (rest ys))))))