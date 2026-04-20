(defn longest-by
  "Return the item in `coll` whose `keyfn` returns the largest value.

  If `coll` is empty, return nil.
  If there is a tie, return the first item with the maximum key value.

  Examples:
  (longest-by count [\"a\" \"bbb\" \"cc\"]) ;=> \"bbb\"
  (longest-by :len [{:len 2} {:len 5} {:len 3}]) ;=> {:len 5}"
  [keyfn coll])