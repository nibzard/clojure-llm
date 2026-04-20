(defn solve
  "You are given a collection xs.
  If xs contains any strings, return a new vector with every string reversed.
  If xs contains no strings, return the collection in reverse order as a vector.
  Nil values should be preserved.
  Examples:
  >>> (solve [1 \"ab\" nil \"C\"])
  [1 \"ba\" nil \"C\"]
  >>> (solve [1 2 3])
  [3 2 1]
  >>> (solve [])
  []"
  [xs]
  (let [has-string? (some string? xs)]
    (vec
     (if has-string?
       (map #(if (string? %) (clojure.string/reverse %) %) xs)
       (reverse xs)))))