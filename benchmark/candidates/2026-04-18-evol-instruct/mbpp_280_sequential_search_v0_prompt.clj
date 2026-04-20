(defn sequential-search
  "Return a map describing the first match of `item` in `coll`.

  The function should work on any sequential collection, including lists,
  vectors, and lazy sequences. Return:
  {:found? true :index n :value x}
  if the item is present, or
  {:found? false :index -1 :value nil}
  if it is not found.

  Examples:
  (sequential-search [1 2 3 4] 3)
  ;; => {:found? true, :index 2, :value 3}

  (sequential-search '(a b c) :z)
  ;; => {:found? false, :index -1, :value nil}"
  [coll item])