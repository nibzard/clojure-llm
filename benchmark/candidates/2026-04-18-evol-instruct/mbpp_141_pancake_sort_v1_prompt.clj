(defn stable-partition
  "Return a vector with all elements satisfying pred first, followed by the rest, preserving the original order.

  Examples:
  (stable-partition even? [1 2 3 4 5 6])
  => [2 4 6 1 3 5]

  (stable-partition pos? [])
  => []"
  [pred coll])