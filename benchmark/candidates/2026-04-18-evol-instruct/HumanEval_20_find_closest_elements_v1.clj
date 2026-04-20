(defn closest-pair
  "Given a vector of at least two integers, return the pair of adjacent values with the smallest absolute difference.
   If multiple adjacent pairs tie for the smallest difference, return the first such pair.
   The returned pair must preserve the original order from the vector.

   Examples:
   >>> (closest-pair [10 3 8 6 7])
   [6 7]
   >>> (closest-pair [1 4 9 10 20])
   [9 10]
   >>> (closest-pair [5 5 2 9])
   [5 5]"
  [v]
  (reduce
   (fn [[best-a best-b best-d] [a b]]
     (let [d (Math/abs (long (- a b)))]
       (if (< d best-d)
         [a b d]
         [best-a best-b best-d])))
   (let [[a b] (take 2 v)]
     [a b (Math/abs (long (- a b)))])
   (partition 2 1 v))
  )