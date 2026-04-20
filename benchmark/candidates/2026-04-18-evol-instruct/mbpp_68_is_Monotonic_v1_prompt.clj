(defn is-monotonic?
  "Return true if the given sequence is monotonic (entirely nondecreasing or nonincreasing).
  Accepts any sequential collection, handles nil as true, and works with empty or single-element collections.

  Examples:
  (is-monotonic? [1 2 2 3]) => true
  (is-monotonic? '(5 4 4 1)) => true
  (is-monotonic? [3 1 2])    => false
  (is-monotonic? nil)        => true"
  [xs])