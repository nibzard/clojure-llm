(defn can-partition-even-sum
  "Return true if the collection can be split into exactly two non-empty groups with equal sum.
  The function should work for vectors, lists, and lazy sequences.

  Examples:
  >>> (can-partition-even-sum [1 5 11 5])
  true
  >>> (can-partition-even-sum [1 2 3 5])
  false
  >>> (can-partition-even-sum [])
  false
  >>> (can-partition-even-sum (range 1 6))
  true"
  [coll]
  (let [xs (vec coll)
        total (reduce + 0 xs)
        n (count xs)]
    (and (pos? n)
         (even? total)
         (let [target (/ total 2)]
           (boolean
            (some true?
                  (for [mask (range 1 (dec (bit-shift-left 1 n)))]
                    (= target
                       (reduce + 0
                               (map (fn [i]
                                      (if (bit-test mask i) (nth xs i) 0))
                                    (range n)))))))))))