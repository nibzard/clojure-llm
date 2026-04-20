(defn sorted-by-length?
  "Return true if the collection is sorted in nondecreasing order by element length.
  Works with strings, vectors, and nil values (nil counts as length 0).

  Examples:
  (sorted-by-length? [nil \"a\" \"bb\"]) => true
  (sorted-by-length? [\"aa\" \"b\"]) => false
  (sorted-by-length? []) => true"
  [coll])