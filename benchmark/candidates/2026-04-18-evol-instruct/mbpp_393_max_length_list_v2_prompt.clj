(defn longest-nonempty-list
  "Return the first longest non-empty list from a collection of lists.

  Ignores nil and empty lists. If multiple lists have the same maximum length,
  returns the first one encountered.

  Examples:
  (longest-nonempty-list [nil '() '(1) '(1 2) '(3 4)])
  ;;=> (1 2)

  (longest-nonempty-list ['() nil])
  ;;=> nil"
  [lists])