(defn sort_array
  "Given a vector of non-negative integers, return a clojure of the given vector after sorting,
  you will sort the given vector in ascending order if the sum( first index value, last index value) is odd,
  or sort it in descending order if the sum( first index value, last index value) is even.
  Note:
  * don't change the given vector."
  [array]
  (if (empty? array)
    []
    (let [first-val (first array)
          last-val (last array)
          sum (+ first-val last-val)
          comparator (if (odd? sum) < >)]
      (vec (sort comparator array)))))