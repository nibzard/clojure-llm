(defn freq-by
  "Return a map of element frequencies after applying f to each item.

  Examples:
  (freq-by count [\"a\" \"bb\" \"ccc\" \"dd\"])
  => {1 1, 2 2, 3 1}

  (freq-by nil? [1 nil 2 nil nil])
  => {false 2, true 3}"
  [f coll])