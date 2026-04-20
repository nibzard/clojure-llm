(defn find-shortest-subseq
  "Return the shortest contiguous subsequence of `coll` that contains all distinct
  elements appearing in `coll`.

  Returns nil for nil or empty input.

  Examples:
  (find-shortest-subseq [1 2 1 3 2]) ;=> [1 3 2]
  (find-shortest-subseq [:a :b :a :c :b :c]) ;=> [:a :c :b :c]"
  [coll])