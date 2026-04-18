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
    (if (or (zero? n) (= 1 n))
      v
      (let [first-val (nth v 0)
            last-val (nth v (dec n))
            sum (long (+ first-val last-val))]
        (if (odd? sum)
          (sort v)
          (sort > v))))))