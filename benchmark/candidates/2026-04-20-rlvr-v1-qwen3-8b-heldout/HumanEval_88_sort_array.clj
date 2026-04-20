(defn sort_array
  "Given a vector of non-negative integers, return a coclj of the given vector after sorting,
  you will sort the given vector in ascending order if the sum( first index value, last index value) is odd,
  or sort it in descending order if the sum( first index value, last index value) is even.
  Note:
  * don't change the given vector.
  Examples:
  >>> (sort_array [])
  []
  >>> (sort_array [5])
  [5]
  >>> (sort_array [2 4 3 0 1 5])
  [0 1 2 3 4 5]
  >>> (sort_array [2 4 3 0 1 5 6])
  [6 5 4 3 2 1 0]"
  [array]
  (let [v (vec array)
        n (count v)]
    (if (zero? n)
      []
      (let [sum (when (>= n 2) (+ (v 0) (v (dec n))))
            ascending? (odd? sum)]
        (if ascending?
          (sort v)
          (sort > v))))))