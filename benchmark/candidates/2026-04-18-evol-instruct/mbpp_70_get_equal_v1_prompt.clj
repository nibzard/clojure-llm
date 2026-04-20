(defn equal-runs?
  "Return true if every consecutive run in the collection has the same length.

  Examples:
  (equal-runs? [[1 2] [:a :b] [10 20]]) ;=> true
  (equal-runs? [[1] [2 3] [4 5]])       ;=> false
  (equal-runs? [])                     ;=> true
  (equal-runs? nil)                    ;=> true"
  [colls])