(defn count-even-prefix-sums
  "Return the number of prefixes of coll whose sum is even.

  Works with any sequence of integers, including lazy and infinite sequences
  when enough elements are present to determine the result.

  Examples:
  (count-even-prefix-sums [1 2 3 4]) ;=> 2
  (count-even-prefix-sums [2 2 2])   ;=> 3
  (count-even-prefix-sums nil)       ;=> 0"
  [coll]
  (loop [xs (seq coll)
         sum 0
         cnt 0]
    (if (nil? xs)
      cnt
      (let [s (+ sum (first xs))
            cnt' (if (even? s) (inc cnt) cnt)]
        (recur (next xs) s cnt')))))