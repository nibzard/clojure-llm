(defn top-n-by
  "Return the n items from `coll` with the highest value of (key-fn item).

  The result is sorted from highest score to lowest score. If `n` is greater
  than the number of items, return all items. If `n` is zero or negative,
  return an empty sequence.

  Examples:
  (top-n-by :price [{:name \"a\" :price 10} {:name \"b\" :price 30} {:name \"c\" :price 20}] 2)
  => ({:name \"b\" :price 30} {:name \"c\" :price 20})

  (top-n-by count [\"a\" \"bbb\" \"cc\"] 1)
  => (\"bbb\")"
  [key-fn coll n])