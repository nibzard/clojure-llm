(defn median-or-nil
  "Return the median of a numeric collection, or nil for an empty collection.
   Accept any seqable input, ignore nil values, and do not mutate the input.

   Examples:
   (median-or-nil [3 1 2]) => 2
   (median-or-nil '(10 4 8 2)) => 6
   (median-or-nil [1 nil 5 3]) => 3
   (median-or-nil []) => nil"
  [xs]
  (let [nums (sort (remove nil? xs))
        n (count nums)]
    (when (pos? n)
      (if (odd? n)
        (nth nums (quot n 2))
        (/ (+ (nth nums (dec (quot n 2)))
              (nth nums (quot n 2)))
           2)))))