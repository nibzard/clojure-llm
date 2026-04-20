(defn cheapest-k
  "Return the k cheapest items from `items`, preserving ascending price order.

  Each item is a map with at least a :price key. If `k` is greater than the
  number of items, return all items sorted by price.

  Examples:
  (cheapest-k [{:name \"a\" :price 10}
               {:name \"b\" :price 5}
               {:name \"c\" :price 7}] 2)
  ;; => ({:name \"b\" :price 5} {:name \"c\" :price 7})"
  [items k]
  (->> items
       (sort-by :price)
       (take k)))