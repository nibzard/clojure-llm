(defn compress-runs
  "Return a lazy sequence of [value run-length] pairs for each consecutive run in `coll`.

  Examples:
  (compress-runs [1 1 1 2 2 3])
  ;; => ([1 3] [2 2] [3 1])

  (compress-runs nil)
  ;; => ()

  Works with any sequential collection."
  [coll])