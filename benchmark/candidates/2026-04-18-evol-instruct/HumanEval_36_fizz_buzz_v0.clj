(defn sum-ratios
  "Return the sum of all positive numbers in a collection that are divisible by 4 or 6, but ignore nil values.
  The function should work on any seqable collection.
  >>> (sum-ratios [1 2 3 4 6 8 nil 12])
  30
  >>> (sum-ratios '(nil 4 5 6 7 8))
  18
  >>> (sum-ratios [])
  0"
  [xs]
  (reduce
   (fn [acc x]
     (if (and x (pos? x) (or (zero? (mod x 4)) (zero? (mod x 6))))
       (+ acc x)
       acc))
   0
   xs))