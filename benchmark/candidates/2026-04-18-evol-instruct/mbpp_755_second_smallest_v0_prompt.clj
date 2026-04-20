(defn second-smallest-distinct
  "Return the second smallest *distinct* number in a collection.

  Ignore nil values. If there are fewer than two distinct numeric values,
  return nil.

  Examples:
  (second-smallest-distinct [3 1 2 1]) ;=> 2
  (second-smallest-distinct [5 5 5])    ;=> nil
  (second-smallest-distinct [nil 4 2 4]) ;=> 4"
  [numbers])