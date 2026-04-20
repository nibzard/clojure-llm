(defn count-even-runs
  "Count the number of contiguous runs of even numbers in a collection.

  A run is a maximal consecutive subsequence of even values.
  nil is treated as an empty collection.

  Examples:
  (count-even-runs [1 2 4 3 6 8 10 5]) ;=> 2
  (count-even-runs [2 4 6])            ;=> 1
  (count-even-runs [1 3 5])            ;=> 0
  (count-even-runs nil)                ;=> 0"
  [xs]
  (->> xs
       (map even?)
       (partition 2 1)
       (filter #(and (false? (first %)) (true? (second %))))
       count))