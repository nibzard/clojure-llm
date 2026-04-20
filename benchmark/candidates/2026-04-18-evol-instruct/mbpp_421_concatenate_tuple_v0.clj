(defn join-when
  "Return a string of the elements in coll joined by `delim`, but only include
  items that satisfy predicate `pred`.

  Examples:
  (join-when even? \",\" [1 2 3 4 5]) ;=> \"2,4\"
  (join-when keyword? \"-\" [\"a\" :b nil :c 1]) ;=> \"b-c\"
  (join-when some? \"/\" nil) ;=> \"\""
  [pred delim coll]
  (->> (or coll [])
       (filter pred)
       (map str)
       (clojure.string/join delim)))