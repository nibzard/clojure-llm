(defn sequential-search
  "Given a possibly nested collection, return a vector with:
   1) whether `item` appears anywhere in the collection, and
   2) the zero-based index of its first occurrence in a flattened view.

   The function should treat nil as empty and work with vectors, lists, and lazy sequences.

   Examples:
   (sequential-search [1 [2 3] 4] 3) ;=> [true 2]
   (sequential-search '((:a) (:b :c)) :d) ;=> [false -1]"
  [coll item]
  (let [xs (flatten (or coll []))
        idx (.indexOf xs item)]
    [(not= idx -1) idx]))