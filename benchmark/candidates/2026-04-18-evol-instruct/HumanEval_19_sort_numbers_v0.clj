(defn sort-numbers-by-key
  "Return the items in ascending order of their associated numeric keys.

  `pairs` is a sequence of [key value] vectors. Sort by key, and when keys are equal,
  preserve the original relative order of those items.

  Examples:
  (sort-numbers-by-key [[3 :c] [1 :a] [2 :b]])
  => ([1 :a] [2 :b] [3 :c])

  (sort-numbers-by-key [[2 \"b\"] [2 \"a\"] [1 \"z\"]])
  => ([1 \"z\"] [2 \"b\"] [2 \"a\"])"
  [pairs]
  (sort-by first pairs))