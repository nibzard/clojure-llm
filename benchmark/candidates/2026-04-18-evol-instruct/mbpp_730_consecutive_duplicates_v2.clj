(defn compress-runs
  "Return a vector of [value count] pairs for each consecutive run in a collection.

  Examples:
  (compress-runs [1 1 1 2 2 3]) ;=> [[1 3] [2 2] [3 1]]
  (compress-runs [])            ;=> []
  (compress-runs nil)           ;=> []"
  [coll]
  (when coll
    (mapv (fn [xs] [(first xs) (count xs)])
          (partition-by identity coll))))