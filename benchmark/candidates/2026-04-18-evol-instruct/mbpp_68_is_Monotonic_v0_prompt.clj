(defn is-monotonic?
  "Return true if the collection is monotonic (entirely nondecreasing or nonincreasing).

  Works with any sequential collection of numbers, including empty and single-element collections.

  Examples:
  (is-monotonic? [])
  ;;=> true

  (is-monotonic? [1 2 2 3])
  ;;=> true

  (is-monotonic? [5 4 4 1])
  ;;=> true

  (is-monotonic? [1 3 2])
  ;;=> false"
  [xs])